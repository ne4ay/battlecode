<template>
  <form @submit.prevent>
    <div class="form-container">
      <div class="form-title">

      </div>
      <pre>
      <div id="editor">
public class Main {
  public static void main(String[] args) {
    System.out.println(1);
  }
}
      </div>
    </pre>
      <SimpleSingleButton type="submit" class="send-button" @click="sendTask">
        Отправить
      </SimpleSingleButton>
    </div>
  </form>
</template>

<script>
import ace from "ace-builds";
import "ace-builds/webpack-resolver";
import SimpleSingleButton from "@/components/forms/SimpleSingleButton";
import axios from "axios";
import Properties from "@/Properties";
import commonUtilsMixin from "@/mixins/commonUtilsMixin";


export default {
  name: "TaskCheckingForm",
  components: {
    SimpleSingleButton
  },
  mixins: [
    commonUtilsMixin
  ],
  props: {
    taskId: {
      type: Number
    },
    activeLang: {
      type: String
    }
  },
  data() {
    return {
      editor: {},
    }
  },
  mounted() {
    this.editor = ace.edit("editor");
    this.editor.setTheme("ace/theme/monokai");
    const mode = "ace/mode/" + this.activeLang.toLowerCase();
    this.editor.session.setMode(mode);
  },
  methods: {
    sendTask() {
      axios.post(Properties.BBACK_ADDRESS + '/process', {
        taskId: this.taskId,
        textOfProgram: this.editor.getValue(),
        language: this.activeLang,
      }, {
        withCredentials: true
      })
      .then(response => {
        console.log(response);
      }).catch(exception => {
        commonUtilsMixin.methods.handleException(exception);
      });
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');
.form-container {
  display: flex;
  flex-direction: column;
}

#editor {
  height: 400pt;
  width: 600pt;
  font-size: 13pt;
  background-color: #181818;
  border-radius: 10pt 10pt 0 10pt;
}
.send-button {
  align-self: end;
  padding: 8pt 20pt 5pt 20pt;
  border-radius: 0 0 20pt 20pt;
  background-color: #181818;
  margin: -25pt 0 15pt 0;
  font-size: 20pt;
  font-family: 'Russo One', sans-serif;
}

</style>