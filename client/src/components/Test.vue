<template>
  <div>
    <el-button type="primary" @click="click">按钮</el-button>
    <el-button type="text" @click="click1">另一个按钮</el-button>
    <p>{{$route.params.id}} : {{$route.query.a}}</p>
  </div>
</template>

<script>
  export default {
    name: "test",
    data()
    {
      return {};
    },
    methods: {
      click()
      {
        this.$router.push('/test/456/?a=87');
      },
      click1()
      {
        this.$router.push('/test/789/?a=98');
      }
    },
    destroyed()
    {
      console.log("destroyed");
    },
    beforeRouteUpdate(to, from, next)
    {
      console.log("update: " + to.path);
      next();
    }
  }
</script>

<style scoped>

</style>