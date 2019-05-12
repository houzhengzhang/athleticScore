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
var Table=antd.Table;
var  Popconfirm=antd.Popconfirm;
const token = JSON.parse(localStorage.getItem("token"));
const comStage=[{competitionStageId:'B47507AE6987430E98BBE646D17350A8',state:'初赛'},{competitionStageId:'BA28BA6C1D7D421796C39C2BF3F397F8',state:'决赛'}];
function fetch_get(url) {
    return encodeURI(encodeURI(url));
}
function changeDate(time) {
    return time.substr(0,time.length-2);
}
const EditableContext = React.createContext();
class EditableCell extends React.Component {
    getInput(){
        return <Select>
            {comStage.map(item => <Option key={item.competitionStageId}>{item.state}</Option>)}
        </Select>;

    }

    render() {
        const {
            editing,
            dataIndex,
            title,
            inputType,
            record,
            index,
            ...restProps
        } = this.props;
        return (
            <EditableContext.Consumer>
                {form => {
                    const { getFieldDecorator } = form;
                    return (
                        <td {...restProps}>
                            {editing===true&&title === '项目阶段' ? (
                                <FormItem style={{ margin: 0 }}>
                                    {getFieldDecorator(dataIndex, {
                                        rules: [
                                            {
                                                required: true,
                                                message: `Please Input ${title}!`
                                            }
                                        ],
                                        initialValue: record[this.props.name]
                                    })(this.getInput())}
                                </FormItem>
                            ) : (
                                restProps.children
                            )}
                        </td>
                    );
                }}
            </EditableContext.Consumer>
        );
    }
}
class EditableTable extends React.Component {
    constructor(props) {
        super(props);

        this.state = { data:this.props.data, editingKey: "" };
        this.columns = [
            {
                title: "项目名",
                dataIndex: "name",
                width: "20%",
                name:"name",
                editable: true
            },
            {
                title: "项目场地地址",
                dataIndex: "competitionField.name",
                name:"fieldId",
                width: "20%",
                editable: true
            },
            {
                title: "开始时间",
                dataIndex:'startTime',
                name:"startTime",
                width: "20%",
                editable: true
            },
            {
                title: "结束时间",
                dataIndex:'endTime',
                name:"endTime",
                width: "20%",
                editable: true
            },
            {
                title: "项目阶段",
                dataIndex: "competitionStage.state",
                name:"competitionStageId",
                width: "10%",
                editable: true
            },
            {
                title: "操作",
                dataIndex: "operation",
                render: (text, record) => {
                    const { editingKey } = this.state;
                    const editable = this.isEditing(record);
                    return (
                        <div>
                            {editable ? (
                                <span>
                    <EditableContext.Consumer>
                      {form => (
                          <a
                              onClick={() => this.save(form, record.key)}
                              style={{ marginRight: 8 }}
                          >
                              Save
                          </a>
                      )}
                    </EditableContext.Consumer>
                    <Popconfirm
                        title="Sure to cancel?"
                        onConfirm={() => this.cancel(record.key)}
                    >
                      <a>Cancel</a>
                    </Popconfirm>
                  </span>
                            ) : (
                                <a
                                    disabled={editingKey !== ""}
                                    onClick={() => this.edit(record.key)}
                                >
                                    修改
                                </a>
                            )}
                        </div>
                    );
                }
            }
        ];
        this.isEditing = record => record.key === this.state.editingKey;
    }
    cancel () {
        this.setState({ editingKey: "" });
    };
    save(form, key) {
        form.validateFields((error, row) => {
            if (error) {
                return;
            }
            const newData = this.state.data;
            console.log(newData);
            const index = newData.findIndex(item => key === item.key);
            if (index > -1) {
                console.log(comStage.findIndex(item => row.competitionStage.state === item.state));
                newData[index].competitionStage=comStage[comStage.findIndex(item => row.competitionStage.state === item.competitionStageId)];
                newData[index].competitionStageId=newData[index].competitionStage.competitionStageId;
                this.setState({ data: newData, editingKey: "" });
                this.props.update(newData[index])
            } else {
                newData.push(row);
                this.setState({ data: newData, editingKey: "" });
            }
        });
    }

    edit(key) {
        this.setState({ editingKey: key });
    }
    render() {
        const components = {
            body: {
                cell: EditableCell
            }
        };
        const columns = this.columns.map(col => {
            if (!col.editable) {
                return col;
            }
            return {
                ...col,
                onCell: record => ({
                    record,
                    dataIndex: col.dataIndex,
                    title: col.title,
                    name: col.name,
                    editing: this.isEditing(record)
                })
            };
        });
        return (
            <EditableContext.Provider value={this.props.form}>
                <Table
                    components={components}
                    dataSource={this.state.data}
                    columns={columns}
                    rowClassName="editable-row"
                    pagination={{
                        onChange: this.cancel,
                        pageSize: 5,
                    }}
                />
            </EditableContext.Provider>
        );
    }
}
class ChangeState extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            data: [],
        };
        this.initDate();
    }
    initDate(){
        let url = '/athletic/CompetitionServlet?method=getAllCompetition';
        fetch(fetch_get(url))
            .then(
                (res) => {
                    return res.json()
                }
            ).then(
            (data) => {
                if(data.status===1){
                    let datas=data.result;
                    for(var key in datas){
                        datas[key].key=key;
                    }
                    this.setState({
                        data:datas
                    });

                }else{
                    message.error("初始化项目信息失败");
                }
            });
    }
    update(row){
        console.log(row);
        let url='/athletic/RefereeServlet?method=updateCompetitionState&competitionId='+row.competitionId+
            '&competitionStageId='+row.competitionStageId+'&endTime='+changeDate(row.endTime)+'&fieldId='+row.fieldId+'&name='
            +row.name+'&startTime='+changeDate(row.startTime);
        fetch(fetch_get(url))
            .then(
                (res) => {
                    return res.json()
                }
            ).then(
            (data) => {
                console.log(data.result);
                if(data.status===1){
                    message.success(data.msg);
                }else {
                    message.error(data.msg);
                }
            });
    }
    render(){
        const EditableFormTable = Form.create()(EditableTable);
        return(
            <Content style={{
                margin: '16px 16px', padding: '4%', background: '#fff',maxHeight: '60%',width:'70%'
            }}
            >
                <EditableFormTable
                    data={this.state.data}
                    update = { this.update.bind(this) }
                />

            </Content>
        );
    }
}
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
                            <Icon type="schedule" />
                            <span>修改项目状态</span>
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
                                    <span style={{fontSize:'15px',color:'#6AAFE6'}}>referee</span>
                                </div>

                            </Col>

                        </Row>
                    </Header>
                    {
                        current=='1'&&<ChangeState />
                    }
                </Layout>
            </Layout>
        );
    }
}

ReactDOM.render(<SiderDemo />, document.body);