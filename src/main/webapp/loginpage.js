var Button = antd.Button;
var Layout = antd.Layout;
var Menu = antd.Menu;
var Header = Layout.Header;
var Content = Layout.Content;
var Footer = Layout.Footer;
var Breadcrumb = antd.Breadcrumb;
var Steps = antd.Steps;
var Step = Steps.Step;
var Select = antd.Select;
var Option = Select.Option;
var Col = antd.Col;
var Row = antd.Row;
var Card = antd.Card;
var Meta = Card.Meta;
var Icon = antd.Icon;
var Avatar = antd.Avatar;
var Progress= antd.Progress;
var TabPane = antd.Tabs.TabPane;
var Tabs = antd.Tabs;
var Popover = antd.Popover;
var List = antd.List;
var Dropdown = antd.Dropdown;
var Search = antd.Input.Search;
var notification = antd.notification;
var Modal = antd.Modal;
var Form = antd.Form;
var FormItem=Form.Item;
var Input=antd.Input;
var message =antd.message;
var Alert=antd.Alert;
var InputNumber=antd.InputNumber;


class LoginForm extends React.Component {
    constructor(props) {
        super(props);
        this.state={
            codeUrl: '/athletic/ValidateCodeServlet?method=getValidateCode'
        };
        this.handleSubmit = (e) => {
            e.preventDefault();
            this.props.form.validateFields((err, values) => {
                if (!err) {
                    fetch('/athletic/UserServlet?method=login&email=' + values.email + "&password="+
                         values.password + "&code=" + values.code + "&authority=" +values.role,
                        {
                            credentials: "include"
                        })
                         .then(
                            (res) => {
                                return res.json()
                            }
                        ).then(
                        (data) => {
                            console.log(data);
                            if(data.status==1){
                                message.success(data.msg);
                                localStorage.setItem("token", JSON.stringify(data.result));
                                window.location=data.url;
                            }else{
                                message.error(data.msg);
                            }

                        });
                }

            });
        }
        this.updateCode = (e) =>{
            e.preventDefault();
            this.setState({
                codeUrl: '/athletic/ValidateCodeServlet?method=getValidateCode'+'&date='+Math.random(),
              });
        }
    }
    
    render() {
      const { getFieldDecorator } = this.props.form;
      return (
        <Form onSubmit={this.handleSubmit} className="login-form">
                    <FormItem>
                        {getFieldDecorator('email', {
                            rules: [{
                                type: 'email', message: 'The input is not valid E-mail!',
                                },{
                                    required: true, message: 'Please input your username!'
                                }],
                        })(
                            <Input size="large" prefix={<Icon type="user" style={{color: '#000',fontSize: '17px'}}/>}
                                   placeholder=" 邮箱"/>
                        )}
                    </FormItem>
                    <FormItem>
                        {getFieldDecorator('password', {
                            rules: [{required: true, message: 'Please input your Password!'}],
                        })(
                            <Input size="large" prefix={<Icon type="lock" style={{color: '#000',fontSize: '17px'}}/>} type="password"
                                   placeholder=" 密码"/>
                        )}
                    </FormItem>
                    <FormItem
                    hasFeedback>
                    {getFieldDecorator('role',{
                        rules: [
                            { required: true, message: '请选择登入权限!' },]
                    })(
                          <Select size='large'  placeholder="请选择登入权限!">
                            <Option value="1"><Icon type="meh" style={{color:'#F68657'}} theme="filled"/> &nbsp;&nbsp;管理员</Option>
                            <Option value="2"><Icon type="meh" style={{color:'#a5dff9'}} theme="filled"/> &nbsp;&nbsp;计分员</Option>
                            <Option value="3"><Icon type="meh" style={{color:'#FFBC42'}} theme="filled"/> &nbsp;&nbsp;裁判员</Option>
                            <Option value="4"><Icon type="meh" style={{color:'#4F86C6'}} theme="filled"/> &nbsp;&nbsp;运动员</Option>
                         </Select>
                        )}
                    </FormItem>
                    <FormItem>
                        <Row gutter={8}>
                            <Col span={16}>
                                {getFieldDecorator('code', {
                                rules: [{required: true, message: 'Please input the code!'}],
                                })(
                                    <Input size="large" min={1000} max={9999} placeholder="4位验证码"/>
                                )}
                            </Col>
                            <Col span={8}>
                            <img onClick={this.updateCode} src = {this.state.codeUrl} height="39" width="90"/>
                            </Col>
                        </Row>
                        
                    </FormItem>
                    
                    <Button style={{width: "100%"}} size="large" type="primary" htmlType="submit" className="login-form-button">
                        登录
                    </Button>
        </Form>
      );
    }
  }
  
  const WrappedNormalLoginForm = Form.create({ name: 'normal_login' })(LoginForm);

ReactDOM.render(
    <Row gutter={16}  justify={'center'} style={{ paddingTop: 20,textAlign:'center',heigth:'100%',width:'100%'}}>
        <Col span={8} />
        <Col span={8} >
        <Card style={{ marginLeft:'10%',marginRight:'10%',paddingBottom: 20}}>
            <img src = 'static/score.png' height="100" width="100"/>
            <div style={{fontSize: "32px",color: '#3E4348',marginTop:10}}>雲计
            <h3 style={{fontSize: "18px",color: '#7C7877'}}>运动会计分系统</h3></div>
            <div style={{marginTop:'20px',marginLeft:'7%',marginRight:'7%'}}><WrappedNormalLoginForm /></div>
        </Card>
    </Col>
        <Col span={8} />
    </Row>
    ,
    document.body
  );
