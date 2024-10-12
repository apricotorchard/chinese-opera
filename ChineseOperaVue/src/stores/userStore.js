// src/stores/userStore.js
import { defineStore } from 'pinia';

export const useUserStore = defineStore('user', {
  state: () => ({
    userId: null, // 用户ID
    userName: '', // 用户名
    preferences: {}, // 用户偏好
  }),
  actions: {
    setUser(userId, userName) {
      this.userId = userId;
      this.userName = userName;
    },
    setPreferences(preferences) {
      this.preferences = preferences;
    },
  },
});
