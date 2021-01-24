<template>
    <div id="app">
        <loader v-if="isLoading" />
        <navbar v-if="!isLoading" />
        <router-view v-if="!isLoading" />
    </div>
</template>

<script>
    import {ref} from "vue";
    import Loader from "@/components/Loader";
    import Navbar from "@/components/Navbar";

    export default {
        components: {Navbar, Loader },
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
    }
</script>

<style scoped lang="scss"></style>