

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
var SubMenu=Menu.SubMenu;
var Tag=antd.Tag;
var Table=antd.Table;
var Radio=antd.Radio;
function fetch_get(url) {
    return encodeURI(encodeURI(url));
}
const token = JSON.parse(localStorage.getItem("token"));
class ComRankPage extends React.Component{
    constructor(){
        super();
        this.data = [
            {
                key:'1',
                name: 'yangdongce1',
                com:'羽毛球',
                score: 100,
                stage:'初赛',
                rank:1,
            },
            {
                key:'2',
                name: 'yangdongce2',
                score: 98,
                stage:'初赛',
                com:'羽毛球',
                rank:2,
            },
            {
                key:'3',
                name: 'yangdongce3',
                score: 95,
                stage:'初赛',
                com:'羽毛球',
                rank:3,
            },
            {
                key:'4',
                name: 'yangdongce4',
                score: 87,
                stage:'初赛',
                com:'羽毛球',
                rank:4,
            },
        ];
    }
    render(){
        const columns = [{
            title:'rank',
            dataIndex:'rank',
        },
            {
                title: 'Name',
                dataIndex: 'name',
                render: text => <a href="javascript:;">{text}</a>,
            },{
                title: 'score',
                dataIndex: 'score',
            }, {
                title: 'state',
                dataIndex: 'state',
            }];
        const data=[];
        return(
            <Content style={{
                margin: '30px 30px', padding: 20, minHeight: 280,background:'#fff'
            }}
            >
                <antd.PageHeader
                    title="项目排名"
                    extra={[
                        <Radio.Group defaultValue="a" >
                            <Radio.Button value="a">初赛</Radio.Button>
                            <Radio.Button value="b">决赛</Radio.Button>
                        </Radio.Group>,
                        <Select defaultValue="lucy" style={{ width: 300 }} >
                            <Option value="lucy">Lucy</Option>
                        </Select>,
                    ]}
                />
                <Table columns={columns} dataSource={this.data} />

            </Content>
        );

    }
}
class ScorePage extends React.Component{
    constructor(props) {
        super(props);

    }
    render(){
        return(
            <Content style={{
                margin: '0px 16px', padding: 24, minHeight: 280,
            }}
            >
                <List
                    grid={{
                        gutter: 40, column: 4,
                    }}
                    dataSource={this.props.data}
                    renderItem={item => (
                        <List.Item>
                            <Card
                                style={{ width: '100%', marginTop: 16 }}
                            >
                                <Meta
                                    avatar={<Avatar size="small" style={{ color: '#fff', backgroundColor: '#1890ff' }}>{item.competition.name[0]}</Avatar>}
                                    title={<Row>
                                        <Col span={8}>{item.competition.name}</Col>
                                        <Col span={8} offset={8} style={{textAlign:'right'}}><antd.Tag color="blue">{item.competitionStage.state}</antd.Tag></Col>
                                    </Row>}
                                    description={
                                        <Row gutter={16}>
                                            <Col span={12}>
                                                <antd.Statistic
                                                    title="分数:"
                                                    value={item.score}
                                                    precision={1}
                                                    valueStyle={{ color: '#4F86C6' }}
                                                    style={{marginTop:20}}
                                                />
                                            </Col>
                                            <Col span={12}>
                                                <antd.Statistic
                                                    title="排名:"
                                                    value={1}
                                                    precision={0}
                                                    valueStyle={{ color: '#F68657' }}
                                                    style={{marginTop:20}}
                                                />
                                            </Col>
                                        </Row>
                                    }
                                />

                            </Card>
                        </List.Item>
                    )}
                />
            </Content>
        );
    }
}
class AthletePage extends React.Component{
    constructor(props) {
        super(props);

    }

    render(){
        const Description = ({ term, children, span = 10 }) => (
            <Col span={span}>
                <div className="description">
                    <div className="term"><strong>{term}</strong></div>
                    <div className="detail">{children}</div>
                </div>
            </Col>
        );
        return(
            <Row style={{marginTop:15}}>
                <Col span={2}>
                    <div>
                        <Avatar style={{marginTop:0,marginLeft:0}} size={80} shape="square" icon="user"/>
                    </div>
                </Col>
                <Description term="姓名">{token.name}</Description>
                <Description term="团队">{token.athleteTeam.name}</Description>
                <Description term="性别">{token.sex===0? '男':'女'}</Description>
                <Description term="邮箱"><a>{token.email}</a></Description>
                <Description term="参加项目" span="12">{this.props.data.map(item => <Tag color="blue">{item}</Tag>)}</Description>

            </Row>
        );
    }
}
class SiderDemo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            current: '1',
            data:[],
            comData:[]
        };
        this.handleClick = (e) => {
            console.log('click ', e.key);
            this.setState({
                current: e.key,
            });
        };
        console.log(token);
    }
    getTags(data){
        var array = new Array();
        for(var i in data){
            array.push(data[i].competition.name);
        }
        return Array.from(new Set(array));
    }
    componentWillMount(){
        let url = '/athletic/AthleteServlet?method=getAthleteScore&athleteId='+token.athleteId;
        fetch(fetch_get(url))
            .then(
                (res) => {
                    return res.json()
                }
            ).then(
            (data) => {
                console.log(data);
                if(data.status===1){
                    message.success(data.msg);
                    this.setState({
                       data:data.result,
                       comData:this.getTags(data.result)
                    });
                }else{
                    message.error(data.msg);
                }
            });
    }
    render() {
        const { current,data,comData } = this.state;
        const extraContent = (
            <img
                src="https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png"
                alt="content"
                height='120'
            />
        );
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
                            <Icon type="solution" />
                            <span>查看分数</span>
                        </Menu.Item>
                        <SubMenu key="sub1" title={<span><Icon type="fund" /><span>查看排名</span></span>}>
                            <Menu.Item key="2"><Icon type="ordered-list" />项目排名</Menu.Item>
                            <Menu.Item key="3"><Icon type="trophy" />运动队排名</Menu.Item>
                        </SubMenu>
                    </Menu>
                </Sider>
                <Layout>
                    <Header style={{ background: '#fff', padding: 0 }}>
                        <Row gutter={16} style={{marginLeft:'1%',marginRight:'0%'}}>
                            <Col span={7} offset={17} style={{textAlign:'right',paddingRight:10}}>
                                <Avatar style={{marginLeft:'17%'}} size={45} icon="user" />
                                <span style={{fontSize:'16px'}}>&nbsp;&nbsp;&nbsp;username <antd.Divider type="vertical" />
                        <span style={{fontSize:'15px',color:'#6AAFE6'}}>athlete&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                </span>
                            </Col>

                        </Row>
                    </Header>
                    <antd.PageHeader
                        onBack={{}}
                        backIcon={<Icon type="idcard" style={{fontSize:20}}/>}
                        title="个人中心"
                        tags={<Tag color="red">运动员</Tag>}
                        style={{marginTop:2,paddingLeft:30,paddingBottom:30}}
                    >
                        <div className="wrap">
                            <div className="content padding"><AthletePage data={comData}/></div>
                            <div className="extraContent">{extraContent}</div>
                        </div>
                    </antd.PageHeader>
                    {
                        current==='1'&&<ScorePage data={data}/>
                    }
                    {
                        current==='2'&&<ComRankPage />
                    }
                </Layout>
            </Layout>
        );
    }
}

ReactDOM.render(<SiderDemo />, document.body);