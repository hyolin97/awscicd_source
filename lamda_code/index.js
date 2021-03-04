
var services = process.env.SERVICES;  // Slack webhook url의 /services/ 다음 문자열
var channel = process.env.CHANNEL;  // 알람을 전송할 Slack channel

var https = require('https');
var util = require('util');

// 타임존을 UTC -> KST로 변경
function toYyyymmddhhmmss(date) {

    if(!date){
        return '';
    }

    function utcToKst(utcDate) {
        return new Date(utcDate.getTime() + 32400000);
    }

    function pad2(n) { return n < 10 ? '0' + n : n }

    var kstDate = utcToKst(date);
    return kstDate.getFullYear().toString()
        + '-'+ pad2(kstDate.getMonth() + 1)
        + '-'+ pad2(kstDate.getDate())
        + ' '+ pad2(kstDate.getHours())
        + ':'+ pad2(kstDate.getMinutes())
        + ':'+ pad2(kstDate.getSeconds());
}

// Slack 메시지 필드 정의
var formatFields = function(event) {
    var fields  = [];

    // Make sure we have a valid response
    if (event) {
        fields = [
            {
                "title" : "type",
                "value" : event['detail-type'],
                "short" : true
            },
            {
                "title" : "time",
                "value" : toYyyymmddhhmmss(new Date(event.time)),
                "short" : true
            },
            {
                "title" : "region",
                "value" : event.region,
                "short" : true
            },
            {
                "title" : "link",
                "value" : "https://"+event.region+".console.aws.amazon.com/codesuite/codepipeline/pipelines/"+event.detail.pipeline+"/executions/"+event.detail['execution-id']+"/timeline?region="+event.region,
                "short" : true
            },
            {
                "title" : "pipeline",
                "value" : event.detail.pipeline,
                "short" : true
            },
            {
                "title" : "execution_id",
                "value" : event.detail['execution-id'],
                "short" : true
            },
            {
                "title" : "state",
                "value" : event.detail.state,
                "short" : true
            }
        ];

    }

    return fields;
};

// Slack 으로 알람 보내는 부분
exports.handler = function(event, context) {

    var postData = {
        "channel": channel, // Slack 알람 받는 채널
        "text": "*" + event.detail.pipeline + " Notify" + "*"   // Slack 알람 제목
    };

    var fields = formatFields(event);

    // Slack color와 메시지 필드
    postData.attachments = [
        {
            "color": event.detail.state == "SUCCESS" ? "good" : (event.detail.state == "STARTED" ? "good" : "danger"),
            "fields": fields
        }
    ];

    var options = {
        method: 'POST',
        hostname: 'hooks.slack.com',
        port: 443,
        path: services  // Slack webhook url의 /services/ 다음 문자열. 위에서 정의됨
    };

    var req = https.request(options, function(res) {
        res.setEncoding('utf8');
        res.on('data', function (chunk) {
            context.done(null);
        });
    });

    req.on('error', function(e) {
        console.log('problem with request: ' + e.message);
    });

    req.write(util.format("%j", postData));
    req.end();
};
