<template>
        <div class="file-table">
            <table>
                <file-table-row v-for="file in fileList"
                                :file="file"/>
            </table>
        </div>

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

<style></style>