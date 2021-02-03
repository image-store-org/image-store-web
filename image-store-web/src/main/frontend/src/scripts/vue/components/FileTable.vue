<template>
        <ul class="file-table">
            <file-table-row v-for="file in fileList"
                            :file="file"/>
        </ul>
</template>

<script lang="ts">
    import { defineComponent, PropType } from "vue";
    import FileTableRow from "@/scripts/vue/components/FileTableRow.vue";

    export default defineComponent({
        name: "file-table",
        components: { FileTableRow },
        props: {
            fileList: {
                type: Object as PropType<File[]>,
                required: true
            }
        }
    });
</script>

<style lang="scss">
    .file-table {
        width: 90%;
        @include respond(mobile) {
            width: 100%;
        }
    }
</style>