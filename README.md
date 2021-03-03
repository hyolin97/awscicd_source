1. DB 연결을 JNDI 멀티 DB connection 처리
  - 1.1 변경된 파일
        - 1.1.1 server.xml, context-datasource.xml, globals.xml
                
  - 1.2 server.xml 파일 변경 내용
=======================>
      	<Context docBase="spring4basic" path="/" reloadable="true" source="org.eclipse.jst.jee.server:spring4basic">
      	</Context>
<=======================
