<template>
  <h1>这是一个商品的详情界面</h1>
  <template>
  <div class="shop-detail">
    <!-- 商品图片 -->
    <el-carousel height="300px" indicator-position="outside">
      <el-carousel-item v-for="(img, index) in imageList" :key="index">
        <img :src="img" class="shop-image" />
      </el-carousel-item>
    </el-carousel>

    <!-- 商品信息 -->
    <div class="shop-info">
      <h2 class="shop-title">{{ shop.name }}</h2>
      <p><strong>区域：</strong> {{ shop.area }}</p>
      <p><strong>地址：</strong> {{ shop.address }}</p>
      <p><strong>评分：</strong> ⭐ {{ shop.score / 10 }}</p>
      <p><strong>价格：</strong> ¥{{ shop.price }}</p>
      <p><strong>描述：</strong> {{ shop.description }}</p>
    </div>

    <!-- 操作按钮 -->
    <el-button type="primary" @click="goBack">返回</el-button>
  </div>
</template>
</template>

<script>
import {getShopDetailById} from '@/api/shop'
export default {
  data() {
    return {
      shop: null,
      imageList: [],
    };
  },
  created() {
    this.fetchShopDetails();
  },
  computed: {
    shopId() {
      return this.$route.query.id;
    },
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    fetchShopDetails() {
      console.log("111");
      getShopDetailById(this.shopId).then(res => {
        this.shop = res.data;
        this.imageList = this.shop.images ? this.shop.images.split(",") : [];
      });
    },
  },
}
</script>

<style scoped>
.shop-detail {
  max-width: 1200px;
  margin: auto;
  padding: 20px;
}

.shop-image {
  width: 100%;
  height: auto;
  object-fit: cover;
}

.shop-info {
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  margin-top: 20px;
}

.shop-title {
  font-size: 28px;
  font-weight: bold;
  color: #333;
}

.shop-info p {
  font-size: 16px;
  color: #666;
}

.el-button {
  margin-top: 20px;
}
</style>