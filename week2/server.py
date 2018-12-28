from flask import Flask
from flask import render_template

app = Flask(__name__)

@app.route("/")
def demo():
  return "<b>Als je dit ziet, is alles in orde."

@app.route("/demo", methods=['GET'])
def eigendemo():
    return render_template('demo.html', name='Karel')

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000, debug=True)
