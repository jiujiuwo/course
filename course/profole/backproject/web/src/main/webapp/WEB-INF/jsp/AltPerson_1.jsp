<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>无标题文档</title>
        <link href="css/homePage.css" rel="stylesheet" type="text/css" />
        <script language="JavaScript" src="js/jquery-1.3.1.min.js" charset="utf-8"></script>
        <script language="JavaScript" src="js/jvalidate-1.0.0.js" charset="utf-8"></script>
        <script language="JavaScript" src="js/jmessagebox-1.0.0.js" charset="utf-8"></script>
    </head>
    <script>
        function getId() {
           var id =<%=request.getParameter("id")%>;
            return id;
        }
    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            var data = ${
        jsonarray
        };
            var showData = data[0];
            var id = showData.id;
            var active = showData.active;
            var mac = showData.mac;
            var mobile = showData.mobile;
            var password = showData.password;
            var qq = showData.qq;
            var time = showData.time;
            var username = showData.username;
            var weixin = showData.weixin;
            var birthday = showData.birthday;
            var company = showData.company;
            var department = showData.department;
            var district = showData.district;
            var education = showData.education;
            var name = showData.name;
            var sex = showData.sex;
            var tellphone = showData.tellphone;
            var email = showData.email;
            var thetitle = showData.thetitle;
            var position = showData.position;
            var attribute = showData.attribute;
            var idcard = showData.idcard;
            var name = showData.name;

            $("#id").val(id);
            $("#thetitle").val(thetitle);
            $("#position").val(position);
            $("#attribute").val(attribute);
            $("#idcard").val(idcard);
            $("#name").val(name);

            $("#active").val(active);
            $("#mac").val(mac);
            $("#mobile").val(mobile);
            $("#password").val(password);
            $("#qq").val(qq);
            $("#time").val(time);
            $("#username").val(username);
            $("#weixin").val(weixin);
            $("#birthday").val(birthday);
            $("#company").val(company);
            $("#department").val(department);
            $("#district").val(district);
            $("#education").val(education);
            $("#name").val(name);
            $("#sex").val(sex);
            $("#tellphone").val(tellphone);
            $("#email").val(email);
        });
    </script>

    <script>
        function updateto() {
            location.href = "showoneuser.htm?id=" + getId();
        }
    </script>


    <body>
        <div class="main">
            <div class="top">
                <div class="topleft">
                    <ul style="list-style-type:none">
                        <a href="homePage.htm"><li>首页</li></a>
                        <a href="managerPage.htm"><li>管理员首页</li></a>
                    </ul>
                </div>
                <div class="topright">
                    <ul style="list-style-type:none">
                        <a href=""><li>admin</li></a>
                        <a href="homePage.htm"><li>退出</li></a>	
                    </ul>
                </div>
            </div>
            <div class="altmiddle">
                <form action="altuser.htm" method="post" commandName="user">
                    <div class="altbiankuang">
                        <div class="altzhongc">
                            <div class="adneid">用户id：</div>
                            <div class="adneio"><input  id="id" name="id" type="text" style="margin:7px 5px; width:130px;"  Readonly/></div>
                            <div class="adneid">所在机构：</div>
                            <div class="adneit"><select type="text" path="company" name="company" id="company"
                                                        jvpattern="^\S" jverrortip="所在公司名称不能为空." jvtipid="spt_company" style="margin: 7px 5px; width:130px;" />*

                                <script type="text/javascript">
                                    $(document).ready(function () {
                                        var data = ${arrayorg};
                                        for (var i = 0; i < data.length; i++) {
                                            var showData = data[i];
                                            var company = showData.company;
                                            $("#company").append("<option value='" + company + "'>" + company + "</option>");
                                        }

                                    });
                                </script>
                                </select> <span id="spt_company"></span>
                            </div>
<!--                            <div class="adneid">注册机器mac地址：</div>
                            <div class="adneio"><input  id="mac" name="mac" type="text" style="margin:7px 5px; width:130px;" Readonly/>
                            </div>
                            <div class="adneid">qq号码：</div>
                            <div class="adneit"><input type="text" path="qq" name="qq" id="qq" style="margin:7px 5px; width:130px;"  jvpattern="^\d{5,15}$" jverrortip="填写正确qq号码（5-15位）。" jvtipid="spt_qq"/>
                                <span id="spt_qq"></span></div>-->
                            <div class="adneid">注册时间：</div>
                            <div class="adneio"><input  id="time" name="time" type="text" style="margin-left:5px; width:130px;" Readonly/></div>
                            <div class="adneid">email：</div>
                            <div class="adneit"><input type="text" path="email" name="email" id="email" style="margin:7px 5px; width:130px;" jvpattern="^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$" jverrortip="请填写正确的email地址。" jvtipid="spt_email"/>*
                                <span id="spt_email"></span></div>
                            <div class="adneid">激活状态：</div>
                            <div class="adneio"><select type="text"  path="active" name="active" id="active" style="margin:7px 5px; width:60px;" />
                                <option value="true">true</option>
                                <option value="false">false</option>
                                </select>
                                <span id="spt_active"></span></div>
                            <div class="adneid">登录密码：</div>
                            <div class="adneit"><input type="text" path="password" name="password" id="password" style="margin:7px 5px; width:130px;" />
                                <span id="spt_password"></span></div>
                            <div class="adneid">用户名：</div>
                            <div class="adneio"><input type="text" path="username" name="username" id="username" style="margin:7px 5px; width:130px;" jvpattern="^\S" jverrortip="用户名不能为空." jvtipid="spt_username"/>*
                                <span id="spt_username"></span></div>
                            <div class="adneid">性别：</div>
                            <div class="adneit"><select type="text"  path="sex" name="sex" id="sex" style="margin:7px 5px; width:50px;" />
                                <option value="男">男</option>
                                <option value="女">女</option>
                                </select>
                                </div>
                            <div class="adneid">真实姓名：</div>
                            <div class="adneio"><input type="text" path="name" name="name" id="name" style="margin:7px 5px; width:130px;" jvpattern="^\S" jverrortip="真实姓名不能为空." jvtipid="spt_name"/>*
                                <span id="spt_name" ></span></div>
<!--                            <div class="adneid">微信号码：</div>
                            <div class="adneit"><input type="text" path="weixin" name="weixin" id="weixin" style="margin:7px 5px; width:130px;" jvpattern="^\d{5,15}$" jverrortip="填写正确微信号码（5-15位）。。" jvtipid="spt_weixin"/>
                                <span id="spt_weixin"></span></div>-->
                            <div class="adneid">职称：</div>
                            <div class="adneit"><input type="text" path="thetitle" name="thetitle" id="thetitle" style="margin:7px 5px; width:130px;" jvpattern="^\S" jverrortip="职称不能为空." jvtipid="spt_thetitle"/>*
                                <span id="spt_thetitle" ></span></div>
<!--                            <div class="adneid">出生日期：</div>
                            <div class="adneit"><input type="text" path="birthday" name="birthday" id="birthday" style="margin:7px 5px; width:130px;" jvpattern="^\d{4}-\d{2}-\d{2}$" jverrortip="正确的日期格式yyyy-mm-dd。" jvtipid="spt_birthday"/>
                                <span id="spt_birthday"></span></div>-->
                            <div class="adneid">职务：</div>
                            <div class="adneio"><input type="text" path="position" name="position" id="position" style="margin:7px 5px; width:130px;" jvpattern="^\S" jverrortip="职务不能为空." jvtipid="spt_position"/>*
                                <span id="spt_position" ></span></div>
                            <div class="adneid">移动电话：</div>
                            <div class="adneit"><input type="text" path="tellphone" name="tellphone" id="tellphone" style="margin:7px 5px; width:130px;" jvpattern="^1[3|4|5|8][0-9]\d{4,8}$" jverrortip="请填写正确的手机号码。" jvtipid="spt_tellphone"/>*
                                <span id="spt_tellphone" ></span></div>
                            <div class="adneid">用户类别：</div>
                            <div class="adneit"><select type="text"  path="attribute" name="attribute" id="attribute" style="margin:7px 5px; width:80px;" jvpattern="^\S" jverrortip="请选用户角色" jvtipid="spt_attribute"/>*
                                    <option value="系统管理员">系统管理员</option>
                                    <option value="普通用户">普通用户</option>
                                    <option value="专家">专家</option>
                                    </select>
                                    <span id="spt_attribute"></span></div>
                             <div class="adneid">所在部门：</div>
                                <div class="adneit"><input type="text" path="department" name="department" id="department" style="margin:7px 5px; width:130px;"  />
                                     </span></div>
                            
<!--                            <div class="adneid">身份证号：</div>
                            <div class="adneio"><input type="text" path="idcard" name="idcard" id="idcard" style="margin:7px 5px; width:130px;" jvpattern="^\S" jverrortip="身份证号不能为空." jvtipid="spt_idcard"/>
                                <span id="spt_idcard" ></span>
                            </div>-->
<!--                            <div class="adneid">所在地区：</div>
                            <div class="adneit"><input type="text" path="district" name="district" id="district" style="margin:7px 5px; width:130px;" jvpattern="^\S" jverrortip="所在地区名称不能为空。" jvtipid="spt_district"/>
                                <span id="spt_district" ></span></div>-->
                            <div class="adneid">座机号码：</div>
                            <div class="adneio"><input type="text"  path="mobile" name="mobile" id="mobile" style="margin:7px 5px; width:130px;" jvpattern="^(\d{3,4}\-?)?\d{7,8}$" jverrortip="固定电话号码格式如下（****-*******）." jvtipid="spt_mobile"/>*
                                <span id="spt_mobile"></span></div>
                            <div class="adneid">教育程度：</div>
                            <div class="adneit"><select type="text"  path="education" name="education" id="education" style="margin:7px 5px; width:80px;" jvpattern="^\S" jverrortip="请选择教育程度" jvtipid="spt_education"/>*
                                <option value="小学">小学</option>
                                <option value="初中">初中</option>
                                <option value="高中">高中</option>
                                <option value="大专">大专</option>
                                <option value="中专">中专</option>
                                <option value="本科">本科</option>
                                <option value="研究生">研究生</option
                                ><option value="博士">博士</option>

                                </select>
                                <span id="spt_education"></span></div>

                        </div>
                        <div class="altbutton">
                            <input type="submit" name="button" id="button" style="width:70px; height:20px; border:1px #666 solid; border-radius:10px; float:left;" value="修改" />
                            <input type="reset" name="button" id="button" style="width:70px; height:20px; border:1px #666 solid; border-radius:10px; float:right;" value="重置" />
                        </div>
                    </div>
                </form>


            </div>
            <div class="bottom">
                <div class="bottomcss">2014 科迪智标 京ICP证000000号</div>
            </div>
        </div>
    </body>
    <script language="JavaScript">
        
$.jMessageBox.settings.yesButtonText = '确定';
$('form').jValidate({
blurvalidate: true
});
    </script>
</html>
