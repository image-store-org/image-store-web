<template>
        <table class="file-table">
            <tr v-for="file in fileList">
                {{ file.name }}
            </tr>
        </table>
</template>

<script lang="ts">
    import { defineComponent, PropType } from "vue";
    import { useToast } from "primevue/usetoast";

    export default defineComponent({
        name: "file-table",
        props: {
            fileList: {
                type: Object as PropType<File[]>,
                required: true
            }
        },
        setup() {
            const toast = useToast();
            return {
                toast
            }
        },
        data() {
            return {
            }
        },
        methods: {
        }
    });
</script>

<style></style>