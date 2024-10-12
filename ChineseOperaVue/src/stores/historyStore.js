// src/stores/historyStore.js
import { defineStore } from 'pinia';

export const useHistoryStore = defineStore('history', {
  state: () => ({
    playHistory: [], // 存储用户的播放历史
  }),
  actions: {
    addToHistory(trackId) {
      if (!this.playHistory.includes(trackId)) {
        this.playHistory.push(trackId);
      }
    },
    clearHistory() {
      this.playHistory = [];
    },
  },
});
