<template>
    <div class="file">
        File
        <router-view></router-view>
    </div>
</template>

<script type="application/javascript">
    import {defineComponent} from "vue";
    import Upload from "@/components/Upload.vue";

    export default defineComponent({
        components: { Upload }
    });
</script>
