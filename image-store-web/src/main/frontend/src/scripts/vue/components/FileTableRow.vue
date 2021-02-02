<template>
    <tr class="file-table-row">
        <td><img :src="fileUrl" :alt="file.name"/></td>
        <td>{{ file.name }}</td>
        <td>{{ fileUtils.formatBytes(file.size, 0) }}</td>
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

<style scoped>

</style>