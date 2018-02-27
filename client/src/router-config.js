import test from './components/Test.vue';

export default [
  {
    path: '/test/:id', components: {test: test}, name: '测试'
  },
  // {
  //   path: '/', redirect: '/test'
  // }
];