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
var PageHeader=antd.PageHeader;
var Table=antd.Table;
var Popconfirm =antd.Popconfirm;
const token = JSON.parse(localStorage.getItem("token"));


const EditableContext = React.createContext();
class EditableCell extends React.Component {
  getInput(){
    if (this.props.title === "state") {
      return <Select >
            <Option value="空闲">空闲</Option>
            <Option value="占有">占有</Option>
            </Select>;
    }
    return <Input />;
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
              {editing ? (
                <FormItem style={{ margin: 0 }}>
                  {getFieldDecorator(dataIndex, {
                    rules: [
                      {
                        required: true,
                        message: `Please Input ${title}!`
                      }
                    ],
                    initialValue: record[dataIndex]
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
    if(this.props.columns==='place'){
    this.columns = [
      {
        title: "name",
        dataIndex: "name",
        width: "45%",
        editable: true
      },
      {
        title: "address",
        dataIndex: "address",
        width: "40%",
        editable: true
      },
      {
        title: "state",
        dataIndex: "state",
        width: "10%",
        editable: true
      },
      {
        title: "operation",
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
    }
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
      this.props.update(row);
      const newData = [...this.state.data];
      const index = newData.findIndex(item => key === item.key);
      if (index > -1) {
        const item = newData[index];
        newData.splice(index, 1, {
          ...item,
          ...row
        });
        this.setState({ data: newData, editingKey: "" });
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
          inputType: col.dataIndex === "age" ? "number" : "text",
          dataIndex: col.dataIndex,
          title: col.title,
          editing: this.isEditing(record)
        })
      };
    });
    return (
      <EditableContext.Provider value={this.props.form}>
        <Table
          components={components}
          bordered
          dataSource={this.state.data}
          columns={columns}
          rowClassName="editable-row"
          pagination={{
            onChange: this.cancel
          }}
        />
      </EditableContext.Provider>
    );
  }
}

class ChangePlace extends React.Component{
  constructor(props) {
    super(props);
    var data = [];
    this.state = {
      data: data,
    };
  }
  initDate(){
    fetch('/athletic/CompetitionFieldServlet?method=getAllCompetitionField')
          .then(
            (res) => {
                return res.json()
            }
        ).then(
        (data) => {
            console.log(this.data);
            if(data.status==1){
                message.success(data.msg);
                this.setState={
                  data:data.result,
                }
            }else{
                message.error(data.msg);
            }
        });
  }
  update(row){
      console.log(row);
  }
  render(){
    const EditableFormTable = Form.create()(EditableTable);
    return(
      <Content style={{
        margin: '16px 16px', padding: '4%', background: '#fff',maxHeight: '60%',width:'60%'
        }}
      >
      <EditableFormTable 
      {...this.props}
      data={this.state.data}
      columns={'place'}
      update = { this.update.bind(this) }
      />

      </Content>
    );
  }
}

class AddPlaceForm extends React.Component {
  constructor(props){
    super(props);
    this.handleSubmit = (e) => {
      e.preventDefault();
      this.props.form.validateFields((err, values) => {
        if (!err) {
          console.log('Received values of form: ', values);
          fetch('/athletic/CompetitionFieldServlet?method=addCompetitionField&name=' + values.name+ "&address="+
                         values.address )
                         .then(
                            (res) => {
                                return res.json()
                            }
                        ).then(
                        (data) => {
                              console.log(this.data);
                            if(data.status==1){
                                message.success(data.msg);
                                this.props.form.setFieldsValue({
                                  name: '',
                                  address:''
                                });

                            }else{
                                message.error(data.msg);
                            }
                            
                        });
        }
      });
    }
  }
  render() {
    const { getFieldDecorator } = this.props.form;
    return (
     
        <Form className="Flied-form"
        onSubmit={this.handleSubmit}
        labelCol={{ span: 5 }} wrapperCol={{ span: 16 }}
        >
          <Form.Item
            label="场地名"
            hasfeedback
          >
            {getFieldDecorator('name', {
              rules: [{ required: true, message: 'Please input your name!' }],
            })(
              <Input />
            )}
          </Form.Item>
          <Form.Item
            label="场地地址"
          >
            {getFieldDecorator('address', {
              rules: [{ required: true, message: 'Please select your address!' }],
            })(
              <Input.TextArea rows={3} />
            )}
          </Form.Item>
          <Form.Item
            wrapperCol={{ span: 5, offset: 5 }}
          >
            <Button type="primary" htmlType="submit" >
              Submit
            </Button>
          </Form.Item>
        </Form>
    
    );
  }
}

class AddPlace extends React.Component {
  constructor(props){
    super(props);
  }
  render() {
    const AddPlaceFormPage =Form.create()(AddPlaceForm);
    return (
      <Content style={{
        margin: '16px 16px', padding: '6%', background: '#fff',maxHeight: '45%',width:'45%'
        }}
      >
         <AddPlaceFormPage />
      </Content>
     
    
    );
  }
}

class SiderDemo extends React.Component {
    constructor() {
        super();
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
                <Icon type="plus-square" />
                <span>录入场地信息</span>
              </Menu.Item>
              <Menu.Item key="2">
                <Icon type="edit" />
                <span>修改场地信息</span>
              </Menu.Item>
              <Menu.Item key="3">
                <Icon type="to-top" />
                <span>创建项目</span>
              </Menu.Item>
              <Menu.Item key="4">
                <Icon type="highlight" />  
                <span>修改项目信息</span>
              </Menu.Item>
              <Menu.Item key="5">
                <Icon type="team" />
                <span>录入运动队</span>
              </Menu.Item>
              <Menu.Item key="6">
                <Icon type="user" />
                <span>录入运动员</span>
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
                    <div style={{fontSize:'16px'}}>yangdongce<antd.Divider type="vertical" />
                        <span style={{fontSize:'15px',color:'#6AAFE6'}}>administor</span>
                    </div>  
                </Col>
                
            </Row>
            </Header>
            <Breadcrumb style={{ marginTop:'40px',marginLeft:'16px' }}>
              <Breadcrumb.Item>管理员</Breadcrumb.Item>
              <Breadcrumb.Item>录入场地信息</Breadcrumb.Item>
            </Breadcrumb>
            {
              current == '1' && <AddPlace />
            }
            {
              current == '2' && <ChangePlace />
            }
            
            
          </Layout>
        </Layout>
      );
    }
  }
  
  ReactDOM.render(<SiderDemo />, document.body);