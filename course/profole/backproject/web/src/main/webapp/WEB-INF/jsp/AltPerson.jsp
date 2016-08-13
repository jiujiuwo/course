<%@page import="com.lifeful.resource.User"%>
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
        <link href="css/backstage_style.css" rel="stylesheet" type="text/css"/>
            <link href="css/backstage_bootstrap.min.css" rel="stylesheet" type="text/css" />
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
 

    <body bgcolor="#ECECEC">
        <div class="mttop">
            <div class="perpic"></div>
            人员修改</div>
        <div class="mtotm">
            <form action="altuser.htm" method="post" commandName="user">
                <div class="permiddle">
                    <div class="perceng">
                        <div class="perleft">用户id：</div>
                        <div class="perright"><input name="id" id="id" type="text" style="width:50%; height:30px;" readonly/>
                            </div>
                    </div>
                    
                    <div class="perceng">
                        <div class="perleft">用户名：</div>
                        <div class="perright"><input name="username" id="username" type="text" style="width:50%; height:30px;" jvpattern="^\S" jverrortip="用户名不能为空." jvtipid="spt_username"/>*
                            <span id="spt_username"></span></div>
                    </div>
                    <div class="perceng">
                        <div class="perleft">真实姓名：</div>
                        <div class="perright"><input name="name" id="name" type="text" style="width:50%; height:30px;" jvpattern="^\S" jverrortip="真实姓名不能为空." jvtipid="spt_name"/>*
                            <span id="spt_name" ></span></div>
                    </div>
                    <div class="perceng">
                        <div class="perleft">所在机构：</div>
                        <div class="perright"><select name="company" id="company" jvpattern="^\S" jverrortip="所在公司名称不能为空." jvtipid="spt_company"> <script type="text/javascript">
                            $(document).ready(function () {
                                var data = ${arrayorg};
                                for (var i = 0; i < data.length; i++) {
                                    var showData = data[i];
                                    var company = showData.company;
                                    $("#company").append("<option value='" + company + "'>" + company + "</option>");
                                }

                            });
                                </script>
                            </select> *<span id="spt_company"></span>
                            <input name="bucompany" id="bucompany" type="text" style="width:50%; height:30px; margin-left:3%; font-family: '微软雅黑';" placeholder="选择没有则在此外填写" ></div> 
                    </div>
                    <div class="perceng">
                        <div class="perleft">所在部门：</div>
                        <div class="perright"><input type="text"  style="width:50%; height:30px;" path="department" name="department" id="department" /></div>
                    </div>
                    <div class="perceng">
                        <div class="perleft">教育程度：</div>
                        <div class="perright"><select name="education" id="education"jvpattern="^\S" jverrortip="请选择教育程度" jvtipid="spt_education"/>*
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
                    <div class="perceng">
                        <div class="perleft">性别：</div>
                        <div class="perright"><select name="sex" id="sex"><option>男</option><option>女</option></select></div>
                    </div>
                    <div class="perceng">
                        <div class="perleft">用户类型：</div>
                        <div class="perright"><select name="attribute" id="attribute"jvpattern="^\S" jverrortip="请选用户角色" jvtipid="spt_attribute"/>*
                            <option value="系统管理员">系统管理员</option>
                            <option value="普通用户">普通用户</option>
                            <option value="专家">专家</option>
                            </select>
                            <span id="spt_attribute"></span></div>
                    </div>
                    <div class="perceng">
                        <div class="perleft">登录密码：</div>
                        <div class="perright"><input name="password" id="password" stype="text"  style="width:50%; height:30px;"></div>
                    </div>
                    <div class="perceng">
                        <div class="perleft">职称：</div>
                        <div class="perright"><input name="thetitle" id="thetitle" type="text"  style="width:50%; height:30px;" jvpattern="^\S" jverrortip="职称不能为空." jvtipid="spt_thetitle"/>*
                            <span id="spt_thetitle" ></span></div>
                    </div>
					 <div class="perceng">
                        <div class="perleft">注册时间：</div>
                        <div class="perright"><input  id="time" name="time" type="text" style="width:50%; height:30px;"  Readonly/></div>
                    </div>

                    <div class="perceng">
                        <div class="perleft">职务：</div>
                        <div class="perright"><input name="position" id="position" type="text"  style="width:50%; height:30px;" jvpattern="^\S" jverrortip="职务不能为空." jvtipid="spt_position"/>*
                            <span id="spt_position" ></span></div>
                    </div>
                    <div class="perceng">
                        <div class="perleft">移动电话：</div>
                        <div class="perright"><input name="tellphone" id="tellphone" type="text" style="width:50%; height:30px;"jvpattern="^1[3|4|5|8][0-9]\d{4,8}$" jverrortip="请填写正确的手机号码。" jvtipid="spt_tellphone"/>*
                            <span id="spt_tellphone" ></span></div>
                    </div>
                    <div class="perceng">
                        <div class="perleft">固定电话：</div>
                        <div class="perright"><input name="mobile" id="mobile" type="text" style="width:50%; height:30px;"jvpattern="^(\d{3,4}\-?)?\d{7,8}$" jverrortip="号码格式如下（****-*******）." jvtipid="spt_mobile"/>*
                            <span id="spt_mobile"></span></div>
                    </div>
                    <div class="perceng">
                        <div class="perleft">email：</div>
                        <div class="perright"><input name="email" id="email" type="text" style="width:50%; height:30px;"jvpattern="^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$" jverrortip="请填写正确的email地址。" jvtipid="spt_email"/>*<span id="spt_email"></span></div>
                    </div>
                    <div class="perbtn">
                        <button type="submit"   class="btn btn-danger">修改</button>
                        <button type="submit"  class="btn btn-danger">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
    <script language="JavaScript">
        
$.jMessageBox.settings.yesButtonText = '确定';
$('form').jValidate({
blurvalidate: true
});
    </script>
</html>
