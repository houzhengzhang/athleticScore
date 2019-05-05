var Button = antd.Button;
var Layout = antd.Layout;
var Menu = antd.Menu;
var Header = Layout.Header;
var Content = Layout.Content;
var Footer = Layout.Footer;
var Sider = Layout.Sider;
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

class SiderDemo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            current: '1',
          }
        this.handleClick = (e) => {
            console.log('click ', e.key);
            this.setState({
              current: e.key,
            });
          }
    }
    changeNav(){
        console.log('sss');
    }
    render() {
        const { current } = this.state;
      return (
        <Layout style={{height:'100%'}}>
          <Sider
            trigger={null}
            collapsible
            style={{}}
          >
            <div style={{marginLeft:'10%',marginRight:'10%'}}>
                <img src = 'static/score.png' style={{ height: '100px',margin: '16px'}}/>
            </div>
            <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']} 
            onClick={this.handleClick}
            selectedKeys={[this.state.current]}
            >
              <Menu.Item key="1">
              <Icon type="ordered-list" />
                <span>录入分数</span>
              </Menu.Item>
            </Menu>
          </Sider>
          <Layout>
            <Header style={{ background: '#fff', padding: 0 }}>
            <Row gutter={16} style={{marginLeft:'1%',marginRight:'0%'}}>
                <Col span={1} offset={20} >
                <Avatar style={{marginLeft:'17%'}} size={45} icon="user" />
                </Col>
                <Col span={3} >
                    <div style={{fontSize:'16px'}}>username <antd.Divider type="vertical" />
                        <span style={{fontSize:'15px',color:'#6AAFE6'}}>scoring staff</span>
                    </div>
                    
                </Col>
                
            </Row>
            </Header>
            <Content style={{
              margin: '24px 16px', padding: 24, background: '#fff', minHeight: 280,
            }}
            >
                {
                    current=='1'&&<div></div>
                }
              Content
            </Content>
          </Layout>
        </Layout>
      );
    }
  }
  
  ReactDOM.render(<SiderDemo />, document.body);