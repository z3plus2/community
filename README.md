##学习测试项目
目标制作一个社区

#第二天
利用github OAuthapp借口获得授权，并将用户名UUID存入数据库

#第三天
发布功能把内容存入数据库，主页列表展示数据库中的发布的内容

#第四到六天
内容分页显示，个人内容显示，页面导航模块化

#第七天
把账号识别功能放入拦截器

#第八天
解决登陆登出功能问题

#第九天
mybatisgenerate插件
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
This will run MBG and instruct it to overwrite any existing Java files it may find.
高版本的驱动，无法识别主键从而缺少部分自动生成方法的问题，如果使用的mysql驱动5.x版本的就可以生成，使用6.x 及以上的，那就无法生成了，必须显式设置：useInformationSchema="true"

Catalogs and Schema
MySql does not properly support SQL catalogs and schema. If you run the create schema command in MySql, it actually creates a database - and the JDBC driver reports it back as a catalog. But MySql syntax does not support the standard catalog..table SQL syntax.

For this reason, it is best to not specify either catalog or schema in generator configurations. Just specify table names and specify the database in the JDBC URL.

If you are using version 8.x of Connector/J you may notice that the generator attempts to generate code for tables in the MySql information schemas (sys, information_schema, performance_schema, etc.) This is probably not what you want! To disable this behavior, add the property "nullCatalogMeansCurrent=true" to your JDBC URL.

For example:

    <jdbcConnection driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://localhost/my_schema"
            userId="my_user" password="my_password">
        <property name="nullCatalogMeansCurrent" value=true" />
    </jdbcConnection>
    
#第十天
错误处理
阅读数功能，反向工程的基础，自建一个手工mapper，注解开发需要的sql

#第十一二天
增加评论功能，AJAX方式增加评论
MBG自动生成的语句，默认只有基本项，blob是text之类的项，查询时注意

#第十三天
增加最热主题功能，利用https://spring.io/guides/gs/scheduling-tasks/