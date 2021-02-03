<template>
    <main id="app">
        <loader v-if="isLoading" />
        <navbar v-if="!isLoading" />
        <router-view v-if="!isLoading" />
        <toast position="bottom-left" />
    </main>
</template>

<script>
    import { defineComponent } from "vue";
    import { ref } from "vue";
    import Loader from "@/scripts/vue/components/Loader";
    import Navbar from "@/scripts/vue/components/Navbar";
    import Toast from "primevue/toast";

    export default defineComponent({
        components: { Navbar, Loader, Toast },
        setup() {
            const isLoading = ref(true);
            document.onreadystatechange = () => {
                setTimeout(() => {
                    isLoading.value = document.readyState !== "complete";
                }, 1000);
            }
            return {
                isLoading
            }
        }
    });
</script>

<style lang="scss">
    @import "src/css/scss/base/html";
    @import "../../css/scss/overrides/p-card";
    @import "../../css/scss/overrides/p-toast";
</style>