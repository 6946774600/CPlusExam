使用系统注意事项
1. 修改关于数据库的配置文件
2. 修改日志的输出文件夹
3. 系统文件上传路径
4. 修改项目使用服务器
    因为文件资源存放于服务期磁盘中，所以 使用这种方式  直接用浏览器访问磁盘文件
    若为eclipse  server配置添加<Context path="/CPlusExamImage" docBase="F:\fileUpLoad\CPlusExam\image" debug="0" reloadable="true" />配置
    若为idea  配置tomcat  添加/CPlusExamImage   指向具体的图片信息文件夹
