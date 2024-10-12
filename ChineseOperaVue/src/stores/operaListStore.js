import { defineStore } from 'pinia';

export const useOperaListStore = defineStore('operaList', {
  state: () => ({
    operaList: [], // 存储所有的戏曲列表
  }),
  actions: {
    setOperaList(list) {
      this.operaList = list;
    },
    addOpera(opera) {
      this.operaList.push(opera);
    },
  },
});