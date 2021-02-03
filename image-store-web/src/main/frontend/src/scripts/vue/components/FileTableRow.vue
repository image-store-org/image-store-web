<template>
    <li class="file-table__row">
        <div class="file-table__cell name"
             :title="file.name">
            {{ file.name }}
        </div>
        <div class="file-table__cell size">
            {{ fileUtils.formatBytes(file.size, 0) }}
        </div>
        <div class="file-table__cell preview">
            <img :src="fileUrl"
                 :alt="file.name" />
        </div>
        <div class="file-table__cell delete">
            <Button icon="pi pi-times"
                    class="p-button-text"
                    @click="deleteFile(index, file)" />
        </div>
    </li>
</template>

<script lang="ts">
    import { defineComponent, PropType } from "vue";
    import FileUtils from "@/scripts/ts/utils/FileUtils";
    import Button from "primevue/button";

    export default defineComponent({
        components: { Button },
        name: "file-table-row",
        props: {
            file: {
                type: Object as PropType<File>,
                required: true
            },
            index: {
                type: Object as PropType<number>,
                required: true
            }
        },
        data() {
            const fileUrl: string = URL.createObjectURL(this.file);
            return {
                fileUtils: FileUtils,
                fileUrl
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
    $row__height: 60px;
    li.file-table__row {
        display: flex;
        border-bottom: 1px solid black;
        height: $row__height;
    }
    li.file-table__row > div {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
    }
    li.file-table__row .file-table__cell.name {
        justify-content: flex-start;
        flex-basis: 50%;
        overflow: hidden;
    }
    li.file-table__row .file-table__cell.size {
        flex-basis: 20%;
    }
    li.file-table__row .file-table__cell.preview, .file-table__cell.delete {
        flex-basis: 15%;
    }
    li.file-table__row .file-table__cell img {
        width: 25px;
    }
    li.file-table__row .file-table__cell button .pi.pi-times {
        color: red;
    }
</style>