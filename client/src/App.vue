<template>
  <div id="app">
    <!--<img src="./assets/logo.png"/>-->
    <div id="demo">
      <el-button type="info" @click="startHacking"><i class="el-icon-upload el-icon--left"></i>开始</el-button>
      <div id="btg" style="margin: 10px;">
        <el-button-group>
          <el-button type="primary" icon="el-icon-arrow-left">上一页</el-button>
          <el-button type="primary" icon="el-icon-arrow-right">下一页</el-button>
        </el-button-group>
      </div>
      <div id="radio" style="margin: 10px">
        <el-radio :disabled="dis" v-model="radio" label="1" border>1</el-radio>
        <el-radio v-model="radio" label="2" border>2</el-radio>
      </div>
      <div id="radioButton">
        <el-radio-group v-model="checkedCities" size="small"><!--large medium small mini-->
          <el-radio-button label="北京"></el-radio-button>
          <el-radio-button label="上海"></el-radio-button>
          <el-radio-button label="广州"></el-radio-button>
          <el-radio-button label="深圳"></el-radio-button>
        </el-radio-group>
      </div>
      <div id="checkBox" style="margin: 10px">
        <el-checkbox v-model="checkAll" :indeterminate="isIndeterminate" @change="handleCheckAllChange" border>全选
        </el-checkbox>
        <el-checkbox-group style="margin-top: 10px" text-color="#ff0000" v-model="checkedCities" size="mini"
                           @change="handleCheckedCitiesChange">
          <el-checkbox v-for="city in cities" :label="city" :key="city" border>{{city}}</el-checkbox>
        </el-checkbox-group>
      </div>
      <div id="input" style="margin: 10px auto ">
        <el-input style="width: 120px" v-model="input" size="mini" placeholder="请输入内容" clearable></el-input>
      </div>
      <div id="select">
        <el-select v-model="s6" multiple placeholder="请选择" clearable @change="seleted">
          <el-option
            v-for="item in cs"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            <span style="float: left">{{ item.label }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
          </el-option>
        </el-select>
      </div>
      <div id="cascader">

      </div>
      <div id="switch" style="margin: 5px 0px">
        <el-switch v-model="sw" active-color="#13ce66" inactive-color="#ff4949" active-text="按年付费" inactive-text="按月付费"
                   active-value="1" inactive-value="2">

        </el-switch>
      </div>
      <div id="slider" style="width: 300px;margin: 0 auto">
        <el-slider v-model="esv" :step="3" :max="50" :min="10">

        </el-slider>
      </div>
      <div id="date">
        <el-time-select v-model="d1" :picker-options="{start: '08:30',step: '00:15',end: '18:30'}" placeholder="请选择时间">

        </el-time-select>
        <el-time-select v-model="d3" :picker-options="{star: '08:30', step: '00:10',end: '22:40',minTime: d1}"
                        placeholder="结束时间">

        </el-time-select>
        <el-time-picker v-model="d2" :picker-options="{selectableRange: '18:30:00 - 20:30:00'}" placeholder="任意时间点">

        </el-time-picker>
        <el-time-picker v-model="d4" is-range range-separator="至" start-placeholder="开始" end-placeholder="结束"
                        arrow-control @change="d4Change">

        </el-time-picker>
        <el-date-picker v-model="d5" type="date" placeholder="选择日期">

        </el-date-picker>
        <el-date-picker v-model="d6" type="week" format="yyyy 第 WW 周" placeholder="选择周" @change="d6Change">

        </el-date-picker>
        <el-date-picker v-model="d7" type="datetimerange" start-placeholder="开始" end-placeholder="结束">

        </el-date-picker>
        <el-rate v-model="d8" show-score>

        </el-rate>
        <el-color-picker v-model="d9" show-alpha>

        </el-color-picker>
      </div>
      <div id="tag">
        <el-tag v-for="tag in tags" :key="tag.name" closable :type="tag.type" @close="tagClose(tag)"
                :disable-transitions="false">{{tag.name}}
        </el-tag>
      </div>
      <div id="progress">
        <el-progress :text-inside="true" :stroke-width="18" :percentage="50"></el-progress>
        <el-progress :width="200" :stroke-width="1" :percentage="40" type="circle" status=""></el-progress>

      </div>
      <div id="tree" style="width: 300px;margin: 10px auto;">
        <el-tree show-checkbox :data="treeData" :props="defaultProps" @node-click="nodeClick"
                 @check-change="checkChange">

        </el-tree>
      </div>
      <div id="pager">
        <el-pagination
          background
          small
          layout="total, sizes, prev, pager, next, jumper"
          :total="500">
        </el-pagination>
      </div>
      <div id="badge">
        <el-badge :value="10" :max="5">
          <el-button size="small">评论</el-button>
        </el-badge>
        <br/>
        <el-badge is-dot style="margin: 10px auto">查询</el-badge>
      </div>
      <div id="alert">
        <el-alert ref="alert" title="成功" type="success" show-icon :closeable="true" center></el-alert>
        <el-button style="margin: 10px auto" type="primary" @click="messageShow">点开</el-button>
      </div>
      <div id="prompt">
        <el-button type="primary" @click="promptShow">提示输入</el-button>
      </div>
      <div id="menu" style="text-align: left">
        <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">
          <el-radio-button :label="false">展开</el-radio-button>
          <el-radio-button :label="true">收起</el-radio-button>
        </el-radio-group>
        <el-menu default-active="1-2" @open="menuOpen" @close="menuClose" @select="menuSelect" :collapse="isCollapse">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>导航一</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="1-1">选项一</el-menu-item>
              <el-menu-item index="1-2">选项二</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">导航二</span>
          </el-menu-item>
        </el-menu>
      </div>

      <div id="tab">
        <el-radio-group v-model="tabPosition">
          <el-radio-button label="top">top</el-radio-button>
          <el-radio-button label="right">right</el-radio-button>
          <el-radio-button label="bottom">bottom</el-radio-button>
          <el-radio-button label="left">left</el-radio-button>
        </el-radio-group>
        <el-tabs v-model="activeName" :tab-position="tabPosition" @tab-click="tabClick">
          <el-tab-pane label="用户管理" name="first">
            <span slot="label"><i class="el-icon-date">用户管理</i> </span>
          </el-tab-pane>
          <el-tab-pane label="配置管理" name="second"></el-tab-pane>
          <el-tab-pane label="角色管理" name="third"></el-tab-pane>
          <el-tab-pane label="定时任务补偿" name="fourth"></el-tab-pane>
        </el-tabs>
      </div>
      <div id="steps" style="height: 200px">
        <el-steps :active="active" finish-status="success" direction="vertical">
          <el-step title="步骤 1"></el-step>
          <el-step title="步骤 2"></el-step>
          <el-step title="步骤 3"></el-step>
        </el-steps>

        <el-button style="margin-top: 12px;" @click="nextStep">下一步</el-button>
      </div>
      <div id="popover" style="margin: 100px auto">
        <el-popover ref="pop" trigger="focus" placement="top" width="160" v-model="vsb">
          <p>确定要删除吗？</p>
          <div style="text-align: right;margin: 0">
            <el-button size="mini" type="text" @click="vsb=false">取消</el-button>
            <el-button type="primary" size="mini" @click="vsb = false">确定</el-button>
          </div>
        </el-popover>
        <el-button v-popover:pop>删除</el-button>
      </div>
    </div>
    <!--view显示-->
    <router-link to="/test/123?a=90" tag="li">测试</router-link>
    <transition>
      <keep-alive>
        <router-view name="test"></router-view>
      </keep-alive>
    </transition>

  </div>
</template>

<script>

  const cityOptions = ['上海', '北京', '广州', '深圳'];
  export default {

    data()
    {
      return {
        radio: '0',
        radio2: 0,
        dis: false,
        checked: false,
        checkList: [],
        checkAll: false,
        isIndeterminate: true,
        checkedCities: ["北京", "上海"],
        cities: cityOptions,
        input: '',
        cs: [{value: "beijing", label: '北京'}, {value: "shanghai", label: '上海'}, {
          value: 'guangzhou',
          label: '广州'
        }, {value: 'shenzhen', label: '深圳'}],
        s6: [],
        sw: true,
        esv: 10,
        d1: "",
        d2: "",
        d3: "",
        d4: "",
        d5: "2018-03-02",
        d6: "",
        d7: null,
        d8: 0,
        d9: "#ff0000",
        tags: [{name: '标签0号', type: ''}, {name: '标签1号', type: 'success'}, {name: '标签2号', type: 'info'}, {
          name: '标签3号',
          type: 'warning'
        }, {name: '标签4号', type: 'danger'}],
        treeData: [{label: '根节点', children: [{label: '子节点', children: []}]}],
        defaultProps: {label: 'label', children: 'children'},
        isCollapse: false,
        activeName: '',
        tabPosition: 'top',
        active: 0,
        vsb: false
      }
    },
    mounted()
    {
      let title = this.$refs.alert.title;
      let visible = this.$refs.alert.visible;
      this.$message({message: '加载完毕：' + title + visible, type: 'success', showClose: true, center: true});
    },
    methods: {
      nextStep()
      {
        if (this.active++ > 2) this.active = 0;
      },
      tabClick(tab, event)
      {
        console.log(tab);
        console.log(event);
      },
      menuSelect(key, keyPath)
      {
        console.log(key);
        console.log(keyPath);
      },
      menuClose(key, keyPath)
      {
        console.log(key);
        console.log(keyPath);
      },
      menuOpen(key, keyPath)
      {
        console.log(key);
        console.log(keyPath);
      },
      promptShow()
      {
        this.$prompt('请输入邮箱', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: '^[A-Za-zd]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$',
          inputErrorMessage: '邮箱格式不对',
          //center: true,
        })
          .then(({value}) =>
          {
            this.$message({type: 'success', message: '你输入的邮箱：' + value});
          })
          .catch(() =>
          {
            this.$message({type: 'info', message: '取消输入'})
          });
      },
      messageShow()
      {
        this.$confirm('确认删除？', '提示', {confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning'})
          .then(() =>
          {
            this.$message({message: '删除成功', type: "success"});
          })
          .catch(() =>
          {
            this.$message({message: '删除取消', type: 'info'});
          });
      },
      checkChange(data, checked, indeterminate)
      {
        console.log(data, checked, indeterminate);
      },
      nodeClick(node)
      {
        //console.log(node);
      },
      tagClose(val)
      {
        let i = -1;
        for (let id in this.tags)
        {
          let temp = this.tags[id];
          if (temp.name === val.name)
          {
            i = id;
          }
        }
        console.log(i);
        if (i >= 0)
        {
          this.tags.splice(i, 1);
        }
        console.log(this.tags);
      },
      d6Change(val)
      {
        console.log(val);
      },
      d4Change(val)
      {
        console.log(val);
      },
      seleted(val)
      {
        console.log(val);
        console.log(this.s6);
      },
      handleCheckAllChange(val)
      {
        this.checkedCities = val ? cityOptions : [];
        this.isIndeterminate = false;
      },
      handleCheckedCitiesChange(value)
      {
        let checkedCount = value.length;
        this.checkAll = checkedCount === this.cities.length;
        this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
      },
      startHacking()
      {
        this.$notify({
          type: 'success',
          title: 'It works!',
          message: 'We\'ve laid the ground work for you. It\'s time for you to build something epic!',
          duration: 5000
        });
      }
    }
  }
</script>

<style>
  #app
  {
    font-family: Helvetica, sans-serif;
    text-align: center;
  }
</style>
