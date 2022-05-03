<template>
  <span id="label">
  <span class="bracket">{</span>
  <span id="battle">Battle</span><span id="code">Code</span>
  <span class="bracket">}</span>
  </span>
</template>

<script>
export default {
  name: "MainLogo"
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@500;600;700&display=swap');
* {
  --size-of-logo: 28pt;
}

#label {
  font-family: 'Russo One', sans-serif;
  font-size: var(--size-of-logo);
  display: flex;
  align-items: center;
  -webkit-user-select: none;   /* Chrome/Safari/Opera */
  -moz-user-select: none;      /* Firefox */
  -ms-user-select: none;       /* Internet Explorer/Edge */
  user-select: none;           /* Non-prefixed version, currently */
}
.bracket {
  color: #e36815;
  font-weight: 700;
  font-size: calc(var(--size-of-logo) + 3pt) ;
}
#battle {
  color: #e5e5e5;
  font-weight: 600;
}
#code {
  color: #f7a36a;
  font-weight: 700;
}
</style>