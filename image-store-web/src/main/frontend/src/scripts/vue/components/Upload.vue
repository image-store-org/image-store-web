<template>
    <div class="upload"
         @drop.prevent="OnUploadDragDrop"
         @dragover.prevent="OnUploadDragEnter"
         @dragenter.prevent="OnUploadDragEnter"
         @dragleave="OnUploadDragLeave">
        <div class="file-input-container"
             :class="{ 'file-input--file-hover': isFileHoverContainer }"
             ref="fileInputContainerElement"
             @click="$refs.fileInputElement.click()">
            <input class="file-input"
                   type="file"
                   required
                   multiple
                   accept="image/jpeg"
                   ref="fileInputElement"
                   @change="onFileInputChange"/>
            <i class="file-input__icon pi pi-download"></i>
        </div>

        <div class="preview-list-container--outer">
            <div class="preview-list-container--inner">
                <ul v-if="fileList.length"
                          class="preview-list"
                          ref="previewListElement"
                          v-cloak>
                    <li v-for="file in fileList">
                        {{ file.name }}
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import {defineComponent, ref} from "vue";
    import FileUtils from "@/scripts/ts/utils/FileUtils";
    import { useToast } from "primevue/usetoast";

    export default defineComponent({
        setup() {
            const toast = useToast();
            const fileList = ref<File[]>([]);
            const fileInputContainerElement = ref<HTMLElement | null>(null);
            const fileInputElement = ref<HTMLInputElement | null>(null);
            const previewListElement = ref<HTMLElement | null>(null);
            const filesTotalSizeBytes = ref<number>(0);
            const filesMaxSizeBytes: number = 3495253;
            const isFileHoverContainer = ref<boolean>(false);
            return {
                toast,
                fileList,
                fileInputContainerElement,
                fileInputElement,
                previewListElement,
                filesTotalSizeBytes,
                filesMaxSizeBytes,
                isFileHoverContainer
            }
        },
        methods: {
            OnUploadDragEnter(): void {
                if (!this.isFileHoverContainer) {
                    this.isFileHoverContainer = true;
                }
            },
            OnUploadDragLeave(): void {
                if (this.isFileHoverContainer) {
                    this.isFileHoverContainer = false;
                }
            },
            OnUploadDragDrop(event: any): void {
                this.onFileInputChange(event);
                this.isFileHoverContainer = false;
            },
            onFileInputChange(event: any): void {
                let files: FileList = event.target.files || event.dataTransfer.files;
                this._addFiles(files);
                this._clearFileUploadElement();
            },
            _addFile(file: File): void {
                this.fileList.push(file);
                this.filesTotalSizeBytes += file.size;
            },
            _addFiles(files: FileList): void {
                let fileNames: string[] = [];
                for (let i = 0; i < files.length; i++) {
                    const file: File = files[i];
                    if (this._isFileValid(file)) {
                        this._addFile(file);
                        fileNames.push(file.name);
                    } else {
                        return;
                    }
                }
                this._successAddFiles(fileNames);
            },
            _removeFile(previewIndex: number): boolean {
                if (previewIndex >= 0) {
                    const fileSize: number = this.fileList[previewIndex].size;
                    if (this.fileList.splice(previewIndex, 1).length > 0) {
                        this.filesTotalSizeBytes = this.filesTotalSizeBytes - fileSize;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            },
            _removeFiles(): void {
                this.fileList = [];
            },
            _isFileValid(file: File): boolean {
                return !(
                    !this._isFileDuplicationValid(file) ||
                    !this._isFileSizeValid(file) ||
                    !this._isFilesTotalSizeValid()
                );
            },
            _isFileDuplicationValid(file: File): boolean {
                for (let i = 0; i < this.fileList.length; i++) {
                    if (FileUtils.isEqual(this.fileList[i], file)) {
                        this._errorFileDuplicate(file);
                        return false;
                    }
                }
                return true;
            },
            _isFileSizeValid(file: File): boolean {
                let filesTotalSizeBytes: number = this.filesTotalSizeBytes + file.size;
                if (filesTotalSizeBytes > this.filesMaxSizeBytes) {
                    this._errorFileSize(filesTotalSizeBytes, this.filesMaxSizeBytes);
                    return false;
                }
                return true;
            },
            _isFilesTotalSizeValid(): boolean {
                if (this.filesTotalSizeBytes > this.filesMaxSizeBytes) {
                    this._errorFileSize(this.filesTotalSizeBytes, this.filesMaxSizeBytes);
                    return false;
                }
                return true;
            },
            _clearFileUploadElement(): void {
                if (this.fileInputElement) {
                    this.fileInputElement.value = "";
                }
            },
            _successAddFiles(fileNames: string[]): void {
                let summarySubString: string = fileNames.length > 1 ? "Files" : "File";
                let detail: string = fileNames.toString().replace(new RegExp(",", "g"), ", ");
                this.toast.add({
                    severity: "success",
                    summary: `${summarySubString} added successfully`,
                    detail: detail,
                    life: 10000
                });
            },
            _errorFileDuplicate(file: File): void {
                this.toast.add({
                    severity: "error",
                    summary: "Duplicate file",
                    detail:`${file.name} is a duplicate`,
                    life: 10000
                });
            },
            _errorFileSize(totalBytes: number, maxBytes: number): void {
                let totalBytesString: string = "Total file size: " + FileUtils.formatBytes(totalBytes);
                let maxBytesString: string = FileUtils.formatBytes(maxBytes);
                let detail: string = `${totalBytesString}. Max total file size: ${maxBytesString}`;
                this.toast.add({
                    severity:"error",
                    summary: "Size limit exceeded",
                    detail: detail,
                    life: 10000
                });
            }
        }
    });
</script>

<style lang="scss">
    $color-primary: $color-winter-teal;
    $color-secondary: $color-still-water;
    .upload .file-input-container {
        display: flex;
        justify-content: center;
        outline: 2px dashed $color-secondary;
        outline-offset: -10px;
        transition: outline-offset .15s ease-in-out, background-color .15s linear;
        background-color: $color-primary;
        padding: 65px 90px;
        @include respond(desktop) {
            padding: 140px 315px
        }
        &.file-input--file-hover {
            background-color: $color-indigo-white;
            outline-color: $color-columbia-blue;
            outline-offset: -20px;
        }
    }
    .upload .file-input-container .file-input {
        display: none;
    }
    .upload .file-input-container .file-input__icon {
        color: $color-secondary;
        font-size: 100px;
    }
</style>