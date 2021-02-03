<template>
    <article class="home">
        <img svg-inline
             class="background"
             src="./../../../assets/svg/home.svg"
             alt="home" />
    </article>
</template>

<script>
    import {defineComponent} from "vue";

    export default defineComponent({
    });
</script>

<style lang="scss">
    .home {
        position: relative;
    }
    .home svg.background {
        position: absolute;
        pointer-events: none;
        top: 125px;
        left: -70px;
        @include respond(mobile) {
            left: -400px;
        }
        @include respond(desktop) {
            top: -160px;
        }
    }
    .home svg g.whole-card {
        animation: ani 2.7s infinite ease-in-out;
    }
    @keyframes ani {
        0% {
            transform: matrix(1,0,0,1,227,219.0349);
        }
        50% {
            transform: matrix(1,0,0,1,227,229.0349);
        }
        100% {
            transform: matrix(1,0,0,1,227,219.0349);
        }
    }
</style>
