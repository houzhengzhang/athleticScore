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
var Radio = antd.Radio;
const token = JSON.parse(localStorage.getItem("token"));
const EditableContext = React.createContext();
var fielddata_global=[];
var competition_global=[];
function fetch_get(url) {
  return encodeURI(encodeURI(url));
}
function changeDate(time) {
  return time.substr(0,time.length-2);
}
class EditableCell extends React.Component {
  getInput(){
    if (this.props.title === "项目场地地址") {
      return <Select >
        {fielddata_global.map(field => <Option value={field.fieldId}>{field.name}</Option>)}
      </Select>;
    }
    if (this.props.title === "项目阶段") {
      return <Select disabled>
        <Option value='B47507AE6987430E98BBE646D17350A8'>初赛</Option>
        <Option value='BA28BA6C1D7D421796C39C2BF3F397F8'>决赛</Option>
      </Select>;
    }
    if(this.props.title === "场地状态")
    {
      return <Select >
        <Option value='空闲'>空闲</Option>
        <Option value='占有'>占有</Option>
      </Select>
    }
    return <Input />;
  }
  render() {
    const {
      editing,
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
                        {getFieldDecorator(this.props.dataIndex, {
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
    console.log("result:",this.props.data);
    this.state = { data:this.props.data, editingKey: "" };
    if(this.props.columns==='place'){
      this.columns = [
        {
          title: "场地名",
          dataIndex: "name",
          name:"name",
          width: "30%",
          editable: true
        },
        {
          title: "场地地址",
          dataIndex: "address",
          name:"address",
          width: "40%",
          editable: true
        },
        {
          title: "场地状态",
          dataIndex: "state",
          name:"state",
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
      if(this.props.columns==='place'){
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

      }
      else{
        const newData = [...this.state.data];
        const index = newData.findIndex(item => key === item.key);
        if (index > -1) {
          newData[index].competitionField=fielddata_global[fielddata_global.findIndex(item => row.competitionField.name === item.fieldId)];
          newData[index].fieldId= row.competitionField.name;
          newData[index].name=row.name;
          newData[index].endTime=row.endTime;
          newData[index].startTime=row.startTime;
          this.setState({ data: newData, editingKey: "" });
          this.props.update(newData[index])
        } else {
          newData.push(row);
          this.setState({ data: newData, editingKey: "" });
        }
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
                pageSize:5
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
          const rangeTimeValue = values['rangetimepicker'];
          let startTime=rangeTimeValue[0].format('YYYY-MM-DD HH:mm:ss');
          let endTime=rangeTimeValue[1].format('YYYY-MM-DD HH:mm:ss');
          let url='/athletic/CompetitionServlet?method=addCompetition&name='
              +values.name+'&fieldId='+values.address+'&startTime='+startTime+'&endTime='+endTime;
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
                  this.props.update();
                  this.props.form.setFieldsValue({
                    name: '',
                    address:'',
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
            {getFieldDecorator('rangetimepicker', rangeConfig)(
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
      fieldData: fielddata_global,
    };

  }
  update(){
    let url = '/athletic/CompetitionServlet?method=getAllCompetition';
    fetch(fetch_get(url))
        .then(
            (res) => {
              return res.json()
            }
        ).then(
        (data) => {
          console.log(data);
          if(data.status===1){
            competition_global=data.result;
          }else{
            message.error("初始化项目信息失败");
          }
        });
  }
  render() {
    const CreateComFormPage =Form.create()(CreateComForm);
    return (
        <Content style={{
          margin: '16px 16px', padding: '6%', background: '#fff',maxHeight: '55%',width:'50%'
        }}
        >
          <CreateComFormPage fieldData={this.state.fieldData} update={this.update.bind(this)}/>
        </Content>


    );
  }
}
class ChangeCom extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
      data: []
    };
  }
  componentWillMount(){
    var datas=competition_global;
    for(let i=0;i<datas.length;i++){
      datas[i].key=datas[i].competitionId;
    }
    this.setState({
      data: datas
    });
  }
  update(row){
    console.log(row);
    let url='/athletic/CompetitionServlet?method=updateCompetition&competitionId='+row.competitionId+
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
          margin: '16px 16px', padding: '4%', background: '#fff',maxHeight: '70%',width:'95%'
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
          let url = '/athletic/AthleteTeamServlet?method=addAthleteTeam&name='+values.name+'&school='+values.school;
          fetch(fetch_get(url))
              .then(
                  (res) => {
                    return res.json()
                  }
              ).then(
              (data) => {
                if(data.status===1){
                  message.success(data.msg);
                  this.props.form.setFieldsValue({
                    name: '',
                    school:''
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
    this.handleSubmit = (e) => {
      e.preventDefault();
      this.props.form.validateFields((err, values) => {
        if (!err) {
          values.roleId='4E495D120C40434EAF8B103DA6195E7F';
          console.log('Received values of form: ', values);
          const formData = new FormData();
          for(var key in values){
            if(key==='competitionIdList')
              for(var id in values[key]){
                formData.append(key,values[key][id]);
              }
            else formData.append(key,values[key]);
          }
          let url='/athletic/AthleteServlet?method=addAthlete';
          fetch(url , {
            method:"POST",
            mode:'no-cors',
            // headers: {
            //     "Content-Type": "application/x-www-form-urlencoded",
            // },
            body:new URLSearchParams(formData),
          }).then(
              (res) => {
                return res.json()
              }
          ).then(
              (data) => {
                if(data.status===1){
                  message.success(data.msg);

                  this.props.form.setFieldsValue({
                    name: '', password:'', email:'',athleteTeamId:'',competitionIdList:[]
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

          >
            {getFieldDecorator('sex', {
              rules: [
                { required: true, message: 'Please select your sex!' },
              ],initialValue: '男'
            })(
                <Radio.Group >
                  <Radio value={0}>男</Radio>
                  <Radio value={1}>女</Radio>
                </Radio.Group>
            )}
          </Form.Item>
          <Form.Item
              label="运动队"
              hasFeedback
          >
            {getFieldDecorator('athleteTeamId', {
              rules: [
                { required: true, message: 'Please select your team!' },
              ],
            })(
                <Select>
                  {this.props.teamData.map(team => <Option key={team.athleteTeamId}>{team.name}</Option>)}
                </Select>
            )}
          </Form.Item>
          <Form.Item
              label="参加项目"
          >
            {getFieldDecorator('competitionIdList', {
              rules: [
                { required: true, message: 'Please select your competitions!', type: 'array' },
              ],
            })(
                <Select mode="multiple"
                >
                  {this.props.competitionData.map(item => (
                      <Option  key={item.competitionId}>
                        {item.name}
                      </Option>
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

    this.state={
      teamData: [],
      competitionData:[],
    };

  }
  componentWillMount() {
    let teamUrl = '/athletic/AthleteTeamServlet?method=getAllAthleteTeam';
    fetch(fetch_get(teamUrl))
        .then(
            (res) => {
              return res.json()
            }
        ).then(
        (data) => {
          console.log(data);
          if(data.status===1){
            this.setState({
              teamData:data.result,
              competitionData:competition_global,
            });
          }else{
            message.error(data.msg);
          }
        });
  }
  render() {
    const AddAthleteFormPage =Form.create()(AddAthleteForm);
    return (
        <Content style={{
          margin: '16px 16px', padding: '3%', background: '#fff',maxHeight: '100%',width:'45%'
        }}
        >
          <AddAthleteFormPage teamData={this.state.teamData} competitionData={this.state.competitionData}/>
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
    var datas = fielddata_global;
    for(let i=0;i<datas.length;i++){
      datas[i].key=datas[i].fieldId;
    }
    this.setState({
      data:datas
    });
  }
  update(row,key){
    console.log(row);
    let url='/athletic/CompetitionFieldServlet?method=updateCompetitionField&fieldId='+key+'&address='+row.address+'&name='+row.name+'&state='+row.state;
    fetch(fetch_get(url))
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
          margin: '16px 16px', padding: '4%', background: '#fff',maxHeight: '65%',width:'95%'
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
          let url = '/athletic/CompetitionFieldServlet?method=addCompetitionField&name=' + values.name + "&address=" + values.address;
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
                  this.props.form.setFieldsValue({
                    name: '',
                    address:''
                  });
                  this.props.update();
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
  update(){
    let url = '/athletic/CompetitionFieldServlet?method=getAllCompetitionField';
    fetch(fetch_get(url))
        .then(
            (res) => {
              return res.json()
            }
        ).then(
        (data) => {
          console.log(data);
          if(data.status===1){
            fielddata_global=data.result;
          }else{
            message.error("初始化场地信息失败");
          }
        });
  }
  render() {
    const AddPlaceFormPage =Form.create()(AddPlaceForm);
    return (
        <Content style={{
          margin: '16px 16px', padding: '6%', background: '#fff',maxHeight: '50%',width:'45%'
        }}
        >
          <AddPlaceFormPage update = { this.update.bind(this) }/>
        </Content>


    );
  }
}
class SiderDemo extends React.Component {
  constructor() {
    super();
    this.state = {
      current: '1',
    };
    this.handleClick = (e) => {
      console.log('click ', e.key);
      this.setState({
        current: e.key,
      });
    };
    this.update();
  }
  update(){
    let url = '/athletic/CompetitionFieldServlet?method=getAllCompetitionField';
    fetch(fetch_get(url))
        .then(
            (res) => {
              return res.json()
            }
        ).then(
        (data) => {
          console.log(data);
          if(data.status===1){
            fielddata_global=data.result;
          }else{
            message.error("初始化场地信息失败");
          }
        });
    url = '/athletic/CompetitionServlet?method=getAllCompetition';
    fetch(fetch_get(url))
        .then(
            (res) => {
              return res.json()
            }
        ).then(
        (data) => {
          console.log(data);
          if(data.status===1){
            competition_global=data.result;
          }else{
            message.error("初始化项目信息失败");
          }
        });
  }
  backIndex(){
    console.log("backindex");
      fetch('/athletic/UserServlet?method=loginout')
          .then(
              (res) => {
                  return res.json()
              }
          ).then(
          (data) => {
              if(data.status===1){
                  window.location=data.url;
              }else{
                  message.error(data.msg);
              }

          });
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
              <Row gutter={16} style={{marginLeft:'1%',marginRight:'0%'}} >
                <Col span={7} offset={17} style={{textAlign:'right',paddingRight:10}}>
                  {
                    token.sex===0?  <Avatar style={{marginLeft:'17%'}}  shape="square" src='static/man.svg'/>
                        : <Avatar style={{marginLeft:'17%'}}  shape="square" src='static/woman.svg'/>
                  }
                  <span style={{fontSize:'15px'}}>&nbsp;&nbsp;&nbsp;{token.name} <antd.Divider type="vertical" />
                      <a onClick={this.backIndex} style={{fontSize:'15px',color:'#6AAFE6'}}>Login out&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                  </span>
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