<template>
    <menubar :model="items">
        <template #start>
            <router-link to="/">
                <img alt="logo" src="../assets/logo.png" height="40" class="p-mr-2">
            </router-link>
        </template>
    </menubar>
</template>

<script type="application/javascript">
    import {defineComponent} from "vue";
    import Menubar from "primevue/menubar";

    export default defineComponent({
        components: { Menubar },
        data() {
            return {
                items: [
                    {
                        label:'File',
                        icon:'pi pi-fw pi-file',
                        items: [
                            {
                                label:'Upload',
                                icon:'pi pi-fw pi-plus',
                                to: '/file/upload'
                            },
                            {
                                label:'Browse',
                                icon:'pi pi-fw pi-th-large',
                                to: '/file/browse'
                            }
                        ]
                    },
                    {
                        label:'Display',
                        icon:'pi pi-fw pi-desktop',
                        items:[
                            {
                                label:'Settings',
                                icon:'pi pi-fw pi-cog',
                            }
                        ]
                    },
                    {
                        label:'User',
                        icon:'pi pi-fw pi-user',
                        items:[
                            {
                                label:'Edit',
                                icon:'pi pi-fw pi-user-edit',

                            },
                            {
                                separator: true
                            },
                            {
                                label:'Logout',
                                icon:'pi pi-fw pi-sign-out'
                            }
                        ]
                    }
                ]
            }
        }
    });
</script>

<style type="text/css">
    .p-menubar {
        justify-content: space-between;
    }
    .p-menubar .p-menubar-root-list ul {
        right: 0;
        left: auto;
    }
    .p-menubar .p-menubar-root-list .p-menuitem .p-submenu-list .p-menuitem-icon.pi-sign-out {
        color: red;
    }
</style>