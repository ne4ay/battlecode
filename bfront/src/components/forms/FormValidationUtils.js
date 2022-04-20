export default {
    fieldIsEmpty(field) {
        return (field !== null && field === '')
            || field === null
            || field === undefined;
    },
    fieldIsNotEmpty(field) {
        return !((field !== null && field === '')
            || field === null
            || field === undefined);
    }
}