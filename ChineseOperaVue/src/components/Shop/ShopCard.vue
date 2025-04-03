<template>
  <el-card class="shop-card" @click="viewDetails" style="padding:0">
    <!-- 轮播图 -->
    <el-carousel height="180px" indicator-position="outside">
      <el-carousel-item v-for="(img, index) in imageList" :key="index">
        <img :src="img" class="shop-image" />
      </el-carousel-item>
    </el-carousel>

    <!-- 店铺信息 -->
    <div class="shop-info">
      <h3 class="shop-title">{{ shop.name }}</h3>
      <p class="shop-area"><el-icon><location /></el-icon> {{ shop.area }}</p>
    </div>
  </el-card>
</template>

<script>
import { Location } from "@element-plus/icons-vue";

export default {
  components: {
    Location,
  },
  props: {
    shop: Object,
  },
  computed: {
    imageList() {
      return this.shop.images ? this.shop.images.split(",") : [];
    },
  },
  methods: {
    viewDetails() {
      this.$router.push({ path: "/shop-detail", query: { id: this.shop.id } });
    },
  },
};
</script>

<style scoped>
.shop-card {
  width: 100%;
  cursor: pointer;
  transition: 0.3s;
  text-align: center;
  padding: 5px;
  border-radius: 12px;
  margin: 5px 0; /* 上下有间距 */
}

.shop-card:hover {
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.15);
  transform: translateY(-5px);
}

.shop-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 10px;
}

.shop-info {
  padding: 5px 10px; /* 缩小上下padding */
}

.shop-title {
  font-size: 16px; /* 缩小字体 */
  font-weight: bold;
  color: #333;
  margin-bottom: 5px;
}

.shop-area {
  font-size: 12px; /* 缩小字体 */
  color: #666;
  display: flex;
  align-items: center;
  justify-content: center;
}

.shop-area el-icon {
  margin-right: 5px;
  color: #f56c6c;
}
</style>
