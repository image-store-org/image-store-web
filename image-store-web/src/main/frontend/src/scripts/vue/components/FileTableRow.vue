<template>
    <tr class="file-table-row">
        <td>{{ file.name }}</td>
        <td>{{ fileUtils.formatBytes(file.size, 0) }}</td>
        <td><img :src="fileUrl" :alt="file.name"/></td>
    </tr>
</template>

<script lang="ts">
import { defineComponent, PropType } from "vue";
import FileUtils from "@/scripts/ts/utils/FileUtils";

    export default defineComponent({
        name: "file-table-row",
        props: {
            file: {
                type: Object as PropType<File>,
                required: true
            }
        },
        data() {
            const fileUrl: string = URL.createObjectURL(this.file);
            return {
                fileUtils: FileUtils,
                fileUrl
            }
        }
    });
</script>

<style lang="scss">
    $td__height: 80px;
    $td__width: 80px;
    .file-table-row {
        display: flex;
    }
    .file-table-row td {
        display: flex;
        justify-content: center;
        align-items: center;
        height: $td__height;
        width: $td__width;
    }
    .file-table-row td img {
        width: 25px;
    }
</style>