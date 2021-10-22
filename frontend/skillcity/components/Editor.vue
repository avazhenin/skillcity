<template>
  <ClientOnly>
    <!-- Use the component in the right place of the template -->
    <tiptap-vuetify @input="$emit('input', content)"
                    v-model="content"
                    :extensions="extensions"
                    :card-props="{ flat: true }"
    />
  </ClientOnly>
</template>

<script>
import {
  TiptapVuetify,
  Heading,
  Bold,
  Italic,
  Strike,
  Underline,
  Code,
  Paragraph,
  BulletList,
  OrderedList,
  ListItem,
  Link,
  Blockquote,
  HardBreak,
  HorizontalRule,
  History
} from 'tiptap-vuetify'

export default {
  // specify TiptapVuetify component in "components"
  components: {TiptapVuetify},
  props: {
    set_value: ''
  },
  name: "Editor",
  data: () => ({
    // declare extensions you want to use
    extensions: [
      History,
      Blockquote,
      Link,
      Underline,
      Strike,
      Italic,
      ListItem,
      BulletList,
      OrderedList,
      [
        Heading,
        {
          options: {
            levels: [1, 2, 3]
          }
        }
      ],
      Bold,
      Link,
      Code,
      HorizontalRule,
      Paragraph,
      HardBreak
    ],
    // starting editor's content
    content: ''
  }),
  mounted() {
    if (this.set_value) this.content = this.set_value
    this.$emit('input', this.content)
  }
}
</script>

<style scoped>

</style>
