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

function fetch_get(url) {
    return encodeURI(encodeURI(url));
}

class CardForm extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            data:this.props.data,
            InputValue:''
        };

    }
    onChange(value) {
        this.setState({
            InputValue:value
        });
    }
    update(){
        this.props.update(this.props.data.athleteId,this.state.InputValue);
    }
    render(){
        return(
            <Card
                style={{ width: '100%', marginTop: 16}}
                actions={[<Icon type="edit" onClick={this.update.bind(this)}/>]}
            >

                <Meta style={{height: 90}}
                      avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}
                      title={<Row>
                          <Col span={8}>{this.props.data.athlete.name}</Col>
                          <Col span={5} offset={11}><antd.Tag color="blue">{this.props.data.competition.competitionStage.state}</antd.Tag></Col>
                      </Row>}
                      description={
                          <div>
                              <h4>录入分数:</h4>
                              <InputNumber style={{width:'50%'}}   onChange={this.onChange.bind(this)} />
                          </div>
                      }
                />

            </Card>
        );
    }
}
class AthleticList extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            InputValue : "",
            data:[]
        };

    }
    initData(data){
        console.log(data);
        this.setState({
           data
        });
    }
    update(athleteId,value){
        const newData = this.state.data;
        const index = newData.findIndex(item => athleteId === item.athleteId);
        let row=newData[index];
        let url = '/athletic/ScoringStaffServlet?method=updateAthleteScore&athleteId='+athleteId+'&competitionId='
            +row.competitionId+'&competitionStageId='+row.competitionStageId+'&score='+value;
        fetch(fetch_get(url))
            .then(
                (res) => {
                    return res.json()
                }
            ).then(
            (data) => {

                if(data.status===1){
                    message.success(data.msg);
                    newData[index].score=value;
                    this.setState({
                        data:newData
                    });
                }else{
                    message.error(data.msg);
                }
            });


    }
    getCard(item){
        if(item.score==0){
            return <CardForm data={item} update={this.update.bind(this)}/>
        }
        else{
            return <Card
                style={{ width: '100%', marginTop: 16 }}
                actions={[<Icon type="check" style={{color:'#52c41a'}} />]}
            >

                <Meta
                    style={{height: 90}}
                    avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}
                    title={<Row>
                        <Col span={8}>{item.athlete.name}</Col>
                        <Col span={5} offset={11}><antd.Tag color="blue">{item.competition.competitionStage.state}</antd.Tag></Col>
                    </Row>}
                    description={
                        <antd.Statistic
                            title="分数:"
                            value={item.score}
                            prefix=''
                            precision={1}
                            valueStyle={{ color: '#4F86C6' }}
                        />}
                />

            </Card>
        }
    }
    render(){
        return(
            <Content style={{
                margin: '0px 16px', padding: 10, minHeight: 280,
            }}
            >

            <List
                grid={{
                    gutter: 16, column: 4,
                }}
                dataSource={this.state.data}
                renderItem={item => (
                    <List.Item>
                        {(this.getCard(item))}
                    </List.Item>
                )}
            />
            </Content>
        );
    }
}
class SiderDemo extends React.Component {
    constructor() {
        super();
        this.state = {
            comdata: [],
        };
        this.handleClick = (e) => {
            this.setState({
                current: e.key,
            });
        }
    }
    componentWillMount(){
        let url = '/athletic/CompetitionServlet?method=getAllCompetition';
        fetch(fetch_get(url))
            .then(
                (res) => {
                    return res.json()
                }
            ).then(
            (data) => {

                if(data.status===1){
                    this.setState({
                        comdata:data.result,
                    });
                }else{
                    message.error("获取项目失败");
                }
            });


    }
    update(value){
        let url = '/athletic/ScoringStaffServlet?method=getAllAthleteScore'+'&competitionId='+value;
        fetch(fetch_get(url))
            .then(
                (res) => {
                    return res.json()
                }
            ).then(
            (data) => {
                if(data.status===1){
                    this.refs["child"].initData(data.result);
                }else{
                    message.error("获取项目失败");
                }
            });
    }
    render() {
        const {athleteData } = this.state;
        const routes = [
            {

                breadcrumbName: '计分员',
            },
            {

                breadcrumbName: '录入分数',
            },

        ];
        return (
            <Layout style={{height:'100%'}}>
                <Sider
                    trigger={null}
                    collapsible

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
                        <Row gutter={16} style={{marginLeft:'1%',marginRight:'0%'}} >
                            <Col span={7} offset={17} style={{textAlign:'right',paddingRight:10}}>
                                <Avatar style={{marginLeft:'17%'}} size={45} icon="user" />
                                <span style={{fontSize:'16px'}}>&nbsp;&nbsp;&nbsp;username <antd.Divider type="vertical" />
                        <span style={{fontSize:'15px',color:'#6AAFE6'}}>scoring staff&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </span>
                            </Col>
                        </Row>
                    </Header>
                    <antd.PageHeader
                        title="录入分数"
                        breadcrumb={{ routes }}
                        style={{marginTop:1,paddingLeft:30,paddingBottom:30}}
                    >
                        <Select
                            showSearch
                            suffixIcon={<Icon type="search"/>}
                            style={{ width:300 }}
                            placeholder="选择一个项目"
                            size='large'
                            onChange={this.update.bind(this)}
                        >
                            {this.state.comdata.map(com => <Option key={com.competitionId}>{com.name}</Option>)}
                        </Select>
                    </antd.PageHeader>
                    <AthleticList ref="child"/>
                </Layout>
            </Layout>
        );
    }
}

ReactDOM.render(<SiderDemo />, document.body);