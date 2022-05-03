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
    },
    numberFieldIsNonNegative(field) {
        return this.fieldIsNotEmpty(field) && field >= 0;
    },
    isShorterThan(field, length) {
        return (field + '').length < length;
    }
}