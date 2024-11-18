<!-- <template>
  <div>
    <canvas ref="canvas"></canvas>
  </div>
</template>

<script>
// import Tiff from 'tiff.js';

export default {
  data() {
    return {
      tiffImageUrl: '"D:\\code\\EcologicalPrediction\\生态数据\yeartif\TrueColor_2024.tif"' // TIFF 图片的 URL
    };
  },
  mounted() {
    this.loadTiffImage();
  },
  methods: {
    loadTiffImage() {
      const xhr = new XMLHttpRequest();
      xhr.open('GET', this.tiffImageUrl, true);
      xhr.responseType = 'arraybuffer'; // 以 ArrayBuffer 形式获取数据

      xhr.onload = (event) => {
        const tiff = new Tiff({ buffer: xhr.response });
        const canvas = this.$refs.canvas;

        // 设置 canvas 大小为 TIFF 图片的大小
        canvas.width = tiff.width();
        canvas.height = tiff.height();

        // 绘制 TIFF 图像到 canvas
        const context = canvas.getContext('2d');
        context.drawImage(tiff.toCanvas(), 0, 0);
      };

      xhr.send();
    }
  }
};
</script>

<style scoped>
canvas {
  max-width: 100%; /* 适应容器大小 */
  height: auto;    /* 自适应高度 */
}
</style> -->
