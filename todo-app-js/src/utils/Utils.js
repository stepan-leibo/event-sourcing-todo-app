Array.prototype.unique = function(field) {
    let a = this.concat();
    for(let i=0; i<a.length; ++i) {
        for(let j=i+1; j<a.length; ++j) {
            if((field && a[i][field] === a[j][field]) || (!field && a[i] === a[j])) {
                a[i] = a[j];
                a.splice(j--, 1);
            }
        }
    }

    return a;
};

export function doEverySeconds(min, max, task, timeoutCallback) {
    let random = Math.round(Math.random() * (max * 1000 - min * 1000)) + min * 1000;

    timeoutCallback(setTimeout(function() {
        task();
        doEverySeconds(min, max, task, timeoutCallback)
    }, random));
}