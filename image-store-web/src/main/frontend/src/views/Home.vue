<template>
    <div class="home">
        <about msg="This is a prop" />
    </div>
</template>

<script type="application/javascript">
    import {defineComponent} from "vue";
    import About from "@/components/About.vue";

    export default defineComponent({
        components: { About }
    });
</script>
