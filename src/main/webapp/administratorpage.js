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
var RangePicker = antd.DatePicker.RangePicker;
const token = JSON.parse(localStorage.getItem("token"));
const EditableContext = React.createContext();
class EditableCell extends React.Component {
  getInput(){
    if (this.props.dataIndex === "state") {
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
    console.log("dasdasda:",props.data);
    this.state = { data:this.props.data, editingKey: "" };
    if(this.props.columns==='place'){
    this.columns = [
      {
        title: "场地名",
        dataIndex: "name",
        width: "30%",
        editable: true
      },
      {
        title: "场地地址",
        dataIndex: "address",
        width: "40%",
        editable: true
      },
      {
        title: "场地状态",
        dataIndex: "state",
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
    }else
    {
      this.columns = [
        {
          title: "项目名",
          dataIndex: "name",
          width: "20%",
          editable: true
        },
        {
          title: "项目场地地址",
          dataIndex: "address",
          width: "20%",
          editable: true
        },
        {
          title: "开始时间",
          dataIndex:'startTime',
          width: "20%",
          editable: true
        },
        {
          title: "结束时间",
          dataIndex:'endTime',
          width: "20%",
          editable: true
        },
        {
          title: "项目阶段",
          dataIndex: "state",
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
      
    }
    this.isEditing = record => record.key === this.state.editingKey;
  }
  componentWillReceiveProps() {
      //this.setState({data:this.props.data});
  }

  cancel () {
    this.setState({ editingKey: "" });
  };
  save(form, key) {
    form.validateFields((error, row) => {
      if (error) {
        return;
      }
      this.props.update(row,key);
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
class CreateComForm extends React.Component {
  constructor(props){
    super(props);
    this.handleSubmit = (e) => {
      e.preventDefault();
      this.props.form.validateFields((err, values) => {
        if (!err) {
          console.log('Received values of form: ', values);
          const rangeTimeValue = values['range-time-picker'];
          let startTime=rangeTimeValue[0].format('YYYY-MM-DD HH:mm:ss');
          let endTime=rangeTimeValue[1].format('YYYY-MM-DD HH:mm:ss');
          let url='/athletic/CompetitionServlet?method=addCompetition&name='
           +values.name+'&fieldId='+values.address+'&startTime='+startTime+'&endTime='+endTime;
          fetch(encodeURI(encodeURI(url)))
                         .then(
                            (res) => {
                                return res.json()
                            }
                        ).then(
                        (data) => {
                              console.log(this.data);
                            if(data.status==1){
                                message.success(data.msg);

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
    const rangeConfig = {
      rules: [{ type: 'array', required: true, message: 'Please select time!' }],
    };
    return (
     
        <Form className="Com-form"
        onSubmit={this.handleSubmit}
        labelCol={{ span: 5 }} wrapperCol={{ span: 16 }}
        >
          <Form.Item
            label="项目名"
            hasfeedback
          >
            {getFieldDecorator('name', {
              rules: [{ required: true, message: 'Please input your name!' }],
            })(
              <Input />
            )}
          </Form.Item>
          <Form.Item
            label="项目场地地址"
          >
            {getFieldDecorator('address', {
              rules: [{ required: true, message: 'Please select your address!' }],
            })(
              <Select
              >
                {this.props.fieldData.map(field => <Option key={field.fieldId}>{field.name}</Option>)}
               </Select>
            )}
          </Form.Item>
          <Form.Item
          label="项目时间"
          >
          {getFieldDecorator('range-time-picker', rangeConfig)(
            <RangePicker showTime placeholder={['开始时间', '结束时间']} format="YYYY-MM-DD HH:mm:ss" />
          )}
          </Form.Item>
          <Form.Item
            wrapperCol={{ span: 5, offset: 5 }}
          >
            <Button type="primary" htmlType="submit" >
              创建项目
            </Button>
          </Form.Item>
        </Form>
    
    );
  }
}
class CreateCom extends React.Component {
  constructor(props){
    super(props);
    this.state={
      fieldData: [],
    };

  }
  componentDidMount(){
    fetch('/athletic/CompetitionFieldServlet?method=getAllCompetitionField')
        .then(
            (res) => {
              return res.json()
            }
        ).then(
        (data) => {
          if(data.status===1){
            message.success(data.msg);
            this.datas=data.result;
            this.setState({
              fieldData:this.datas,
            });
          }else{
            message.error(data.msg);
            this.datas=[];
          }
        });
  }
  render() {
    const CreateComFormPage =Form.create()(CreateComForm);
    return (
      <Content style={{
        margin: '16px 16px', padding: '6%', background: '#fff',maxHeight: '50%',width:'50%'
        }}
      >
        <CreateComFormPage fieldData={this.state.fieldData}/>
      </Content>
     
    
    );
  }
}
class ChangeCom extends React.Component{
  constructor(props) {
    super(props);
    var data = [];
    for (let i = 0; i < 5; i++) {
      data.push({
        key: i.toString(),
        name: `Edrward ${i}`,
        address: `London Park no. ${i}`,
        startTime:'2019-05-07 15:04:07',
        endTime:'2019-05-17 15:04:07',
        state:'未开始'
      });
    }
    this.state = {
      data: data,
    };
    this.initDate();
  }
  initDate(){
    fetch('/athletic/Servlet?method=')
          .then(
            (res) => {
                return res.json()
            }
        ).then(
        (data) => {
            console.log(this.data);
            if(data.status==1){
                message.success(data.msg);
                localStorage.setItem("token", JSON.stringify(data.result));
                window.location=data.url;

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
        margin: '16px 16px', padding: '4%', background: '#fff',maxHeight: '60%',width:'70%'
        }}
      >
      <EditableFormTable 
      {...this.props}
      data={this.state.data}
      columns={'com'}
      update = { this.update.bind(this) }
      />

      </Content>
    );
  }
}
class AddAthleteTeamForm extends React.Component {
  constructor(props){
    super(props);
    this.handleSubmit = (e) => {
      e.preventDefault();
      this.props.form.validateFields((err, values) => {
        if (!err) {
          console.log('Received values of form: ', values);
          /*fetch('/athletic/addFliedServlet?method=addFlied&name=' + values.name+ "&address="+
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
                            
                        });*/
        }
      });
    }
  }
  render() {
    const { getFieldDecorator } = this.props.form;
    return (
     
        <Form className="AthleteTeam-form"
        onSubmit={this.handleSubmit}
        labelCol={{ span: 5 }} wrapperCol={{ span: 16 }}
        >
          <Form.Item
            label="队名"
            hasfeedback
          >
            {getFieldDecorator('name', {
              rules: [{ required: true, message: 'Please input your name!' }],
            })(
              <Input />
            )}
          </Form.Item>
          <Form.Item
            label="代表学校"
          >
            {getFieldDecorator('school', {
              rules: [{ required: true, message: 'Please select your school!' }],
            })(
              <Input.TextArea rows={2} />
            )}
          </Form.Item>
          <Form.Item
            wrapperCol={{ span: 5, offset: 5 }}
          >
            <Button type="primary" htmlType="submit" >
              创建运动队
            </Button>
          </Form.Item>
        </Form>
    
    );
  }
}
class AddAthleteTeam extends React.Component {
  constructor(props){
    super(props);
  }
  render() {
    const AddAthleteTeamFormPage =Form.create()(AddAthleteTeamForm);
    return (
      <Content style={{
        margin: '16px 16px', padding: '6%', background: '#fff',maxHeight: '45%',width:'45%'
        }}
      >
         <AddAthleteTeamFormPage />
      </Content>
     
    
    );
  }
}
class AddAthleteForm extends React.Component {
  constructor(props){
    super(props);
    this.competitionData = [];
    for (let i = 0; i < 5; i++) {
      this.competitionData.push({
        id: i.toString(),
        name: `sport ${i}`,
      });
    }
    this.state = {
      teamData:this.competitionData,
      competitionData:this.competitionData,
    };
    this.handleSubmit = (e) => {
      e.preventDefault();
      this.props.form.validateFields((err, values) => {
        if (!err) {
          console.log('Received values of form: ', values);
          /*fetch('/athletic/addFliedServlet?method=addFlied&name=' + values.name+ "&address="+
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
                            
                        });*/
        }
      });
    }
  }
  render() {
    
    const { getFieldDecorator } = this.props.form;
    return (
     
        <Form className="Athlete-form"
        onSubmit={this.handleSubmit}
        labelCol={{ span: 5 }} wrapperCol={{ span: 16 }}
        >
          <Form.Item
            label="邮箱"
            hasfeedback
          >
            {getFieldDecorator('email', {
                rules: [{type: 'email', message: 'The input is not valid E-mail!',},{
                           required: true, message: 'Please input your E-mail!'}],})(
            <Input />
            )}
          </Form.Item>
          <Form.Item
            label="密码"
          >
            {getFieldDecorator('password', {
              rules: [{ required: true, message: 'Please select your password!' }],
            })(
              <Input type='password' />
            )}
          </Form.Item>
          <Form.Item
            label="姓名"
          >
            {getFieldDecorator('name', {
              rules: [{ required: true, message: 'Please select your name!' }],
            })(
              <Input />
            )}
          </Form.Item>
          <Form.Item
          label="性别"
          hasFeedback
          wrapperCol={{ span:5}}
        >
          {getFieldDecorator('sex', {
            rules: [
              { required: true, message: 'Please select your sex!' },
            ],
          })(
            <Select>
              <Option value="男">男</Option>
              <Option value="女">女</Option>
            </Select>
          )}
        </Form.Item>
        <Form.Item
          label="运动队"
          hasFeedback
        >
          {getFieldDecorator('team', {
            rules: [
              { required: true, message: 'Please select your team!' },
            ],
          })(
            <Select>
               {this.state.teamData.map(team => <Option key={team.id}>{team.name}</Option>)}
            </Select>
          )}
        </Form.Item>
        <Form.Item
          label="参加项目"
        >
          {getFieldDecorator('competitions', {
            rules: [
              { required: true, message: 'Please select your competitions!', type: 'array' },
            ],
          })(
            <Select mode="multiple"
             >
              {this.state.competitionData.map(item => (
                  <Select.Option key={item} value={item.id}>
                  {item.name}
                  </Select.Option>
              ))}
            </Select>
          )}
        </Form.Item>
          <Form.Item
            wrapperCol={{ span: 5, offset: 5 }}
          >
            <Button type="primary" htmlType="submit" >
              录入运动员
            </Button>
          </Form.Item>
        </Form>
    
    );
  }
}
class AddAthlete extends React.Component {
  constructor(props){
    super(props);
  }
  render() {
    const AddAthleteFormPage =Form.create()(AddAthleteForm);
    return (
      <Content style={{
        margin: '16px 16px', padding: '6%', background: '#fff',maxHeight: '65%',width:'45%'
        }}
      >
         <AddAthleteFormPage />
      </Content>
     
    
    );
  }
}
class ChangePlace extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
      data:[],
    };

  }
    componentWillMount(){
    fetch('/athletic/CompetitionFieldServlet?method=getAllCompetitionField')
          .then(
            (res) => {
                return res.json()
            }
        ).then(
        (data) => {
            console.log(data.result);
            if(data.status===1){
                message.success(data.msg);
                let datas=data.result;
                for(let i=0;i<datas.length;i++){
                  datas[i].key=datas[i].fieldId;
                }
                this.setState({
                  data:data.result
                });
            }else{
                message.error(data.msg);

            }
        });
  }
  update(row,key){
      console.log(row);
      let url='/athletic/CompetitionFieldServlet?method=updateCompetitionField&fieldId='+key+'&address='+row.address+'&name='+row.name+'&state='+row.state;
      fetch(encodeURI(encodeURI(url)))
          .then(
            (res) => {
                return res.json()
            }
        ).then(
        (data) => {
            if(data.status===1){
                message.success(data.msg);
            }else{
                message.error(data.msg);

            }
        });
  }
  render(){
    const EditableFormTable = Form.create()(EditableTable);
    return(
      <Content style={{
        margin: '16px 16px', padding: '4%', background: '#fff',maxHeight: '60%',width:'95%'
        }}
      >
      <EditableFormTable
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
          fetch('/athletic/CompetitionFieldServlet?method=addCompetitionField&name=' + encodeURI(encodeURI(values.name)) + "&address="+
          encodeURI(encodeURI(values.address)) )
                         .then(
                            (res) => {
                                return res.json()
                            }
                        ).then(
                        (data) => {
                              console.log(data);
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
              添加场地
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
                  <div style={{fontSize:'16px'}}>{token.name}<antd.Divider type="vertical" />
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
          {
            current == '3' && <CreateCom />
          }
          {
            current == '4' && <ChangeCom />
          }
          {
            current == '5' && <AddAthleteTeam />
          }
          {
            current == '6' && <AddAthlete />
          }
          
        </Layout>
      </Layout>
    );
  }
}
  ReactDOM.render(<SiderDemo />, document.body);