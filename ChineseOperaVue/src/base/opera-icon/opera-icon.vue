<template>
  <i
    :class="['iconfont', getIconCls()]"
    :style="getIconStyle()"
    @click="onClick"
  />
</template>

<script>
export default {
  name: 'OperaIcon',
  props: {
    type: {
      type: String,
      required: true,
    },
    size: {
      type: Number,
      default: 16,
    },
  },
  methods: {
    getIconCls() {
      return `icon-${this.type}`;
    },
    getIconStyle() {
      return {
        fontSize: this.size + 'px',
      };
    },
    onClick(e) {
      this.$emit('click', e);
    },
  },
};
</script>

<style lang="scss" scoped>
.iconfont {
  display: inline-block;
  font-style: normal;
  font-weight: normal;
  font-variant: normal;
  line-height: 1;
  vertical-align: baseline;
  text-transform: none;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
