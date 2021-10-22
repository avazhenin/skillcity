<template>
    <iframe
      v-if="isYoutubeUrl"
      :src="youtubeUrl"
      frameborder="0"
      allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
      allowfullscreen
      class="youtubeSize"
    ></iframe>
</template>

<script>
export default {
  name: "YouTubeVideoEmbed",
  props: {
    url: ''
  },
  data() {
    return {
      isYoutubeUrl: false,
      youtubeUrl: ''
    }
  },
  methods: {
    loadURL() {
      const youtubeEmbedTemplate = 'https://www.youtube.com/embed/'
      const url = this.url.split(/(vi\/|v%3D|v=|\/v\/|youtu\.be\/|\/embed\/)/)
      const YId = undefined !== url[2] ? url[2].split(/[^0-9a-z_/\\-]/i)[0] : url[0]
      if (YId === url[0]) {
        // console.log("not a youtube link")
      } else {
        this.isYoutubeUrl = true
      }
      const topOfQueue = youtubeEmbedTemplate.concat(YId)
      this.youtubeUrl = topOfQueue
    }
  },
  mounted() {
    if (this.youtubeUrl != null && this.youtubeUrl != undefined) this.loadURL()
  }
}
</script>

<style scoped>
.youtubeSize {
  width: 100%;
  height: 400px;
}

</style>
