<template>
        <ul class="file-table">
            <file-table-row v-for="(file, index) in fileList"
                            :file="file"
                            :index="index"
                            :key="index"
                            @deleteFile="deleteFile" />
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
        },
        methods: {
            deleteFile(index: number, file: File): void {
                this.$emit('deleteFile', index, file);
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