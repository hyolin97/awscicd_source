import boto3
import json
import logging
import os
import datetime
from base64 import b64decode
from urllib.request import Request, urlopen
from urllib.error import URLError, HTTPError

HOOK_URL = os.environ['HOOK_URL']
SLACK_CHANNEL = os.environ['SLACK_CHANNEL']

KST_TIMEZONE = datetime.timezone(datetime.timedelta(hours=9))

logger = logging.getLogger()
logger.setLevel(logging.DEBUG)

def toKSTfromUTC(date):
    utc_date = datetime.datetime.strptime(date, "%a %b %d %H:%M:%S %Z %Y")
    utc_date = utc_date.replace(tzinfo=datetime.timezone.utc)
    kst_date = utc_date.astimezone(KST_TIMEZONE)

    return kst_date.strftime("%Y년 %m월 %d일 %H시 %M분 %S초 (%Z)")

def chooseSeverity(status):
    """
    Slack 메세지 상태 색상을 배포 상태에 맞게 선택한다.
    """
    if status == "SUCCEEDED":
        severity = "good"       # green
    elif status == "FAILED":
        severity = "danger"     # red
    else:
        severity = "warning"    # yellow

    return severity


def createSlackMessage(snsData):
    """
    Slack에 발송할 메세지 규격을 만든다.
    """

    codeDeployEvent = json.loads(snsData["Message"])

    slack_message_fields = [
        {
            # "color" : severity,
            "color" : chooseSeverity(codeDeployEvent["status"]),
            "fields": [
                        {
                            "title": "배포 트리거",
                            "value": codeDeployEvent["eventTriggerName"],
                            "short": True
                        },
                        {
                            "title" : "배포 상태",
                            "value" : codeDeployEvent["status"],
                            "short" : True
                        },
                        {
                            "title" : "배포 어플리케이션",
                            "value" : codeDeployEvent["applicationName"],
                            "short" : True
                        },
                        {
                            "title" : "배포 그룹",
                            "value" : codeDeployEvent["deploymentGroupName"],
                            "short" : True
                        },
                        {
                            "title" : "지역",
                            "value" : codeDeployEvent["region"],
                            "short" : True
                        },
                        {
                            "title" : "배포 Link",
                            "value" : 'https://%s.console.aws.amazon.com/codedeploy/home?region=%s#/deployments/%s' % (codeDeployEvent["region"],codeDeployEvent["region"],codeDeployEvent["deploymentId"]),
                            "short" : True
                        },
                        {
                            "title" : "배포 시작 시간",
                            "value" : toKSTfromUTC(codeDeployEvent["createTime"]),
                            "short" : True
                        },
                        {
                            "title" : "배포 종료 시간",
                            "value" : toKSTfromUTC(codeDeployEvent["completeTime"]),
                            "short" : True
                        },
                        {
                            "title" : "오류 코드",
                            "value" : json.loads(codeDeployEvent["errorInformation"])["ErrorCode"] if "errorInformation" in codeDeployEvent else "없음",
                            "short" : True
                        },
                        {
                            "title" : "오류 메세지",
                            "value" : json.loads(codeDeployEvent["errorInformation"])["ErrorMessage"] if "errorInformation" in codeDeployEvent else "없음",
                            "short" : True
                        }
                    ]
        }
    ]


    slack_message = {
        'channel'    : SLACK_CHANNEL,
        # 'username'   : "배포 상태 알림",
        'text'       : "*%s*" % snsData["Subject"],
        # "icon_emoji" : ":aws:",
        "attachments": slack_message_fields
    }

    return slack_message


def sendSlack(slack_message):
    logger.debug(json.dumps(slack_message))

    req = Request(HOOK_URL, json.dumps(slack_message).encode('utf-8'))

    response = urlopen(req)
    response.read()

    return True


def lambda_handler(event, context):
    sendSlack(createSlackMessage(event["Records"][0]["Sns"]))