from flask import Flask
from flask import request, render_template, redirect
from flask import url_for

users = [{'name': 'Donald', 'pass': 'Great'},
         {'name': 'Hillary', 'pass': 'Nooooo'},
         {'name': 'Bernie', 'pass': 'Socialism'},
         {'name': 'Barack', 'pass': 'Yes!'}]


def _user(name):
    return next((item for item in users if item['name'] == name), None)


app = Flask(__name__)


# opgave 3b
@app.errorhandler(404)
def errorhandler(error):
    return render_template('404.html'), 404


# opgave 3c
@app.route('/login', methods=['GET'])
def login():
    return render_template('login.html')


@app.route('/login', methods=['POST'])
def checkGegevens():
    inputEmail = request.form['inputEmail']
    inputPassword = request.form['inputPassword']

    if _user(inputEmail) and _user(inputEmail)["pass"] == inputPassword:
        return render_template('dashboard.html', name=inputEmail)
    else:
        return redirect(url_for('login'))

# opgave 3d
# @app.route('/checkgegevens', methods=['POST'])
# def checkGegevens():
#     inputEmail = request.form['inputEmail']
#     inputPassword = request.form['inputPassword']

#     if _user(inputEmail)["pass"] == inputPassword:
#         return render_template('dashboard.html', name=inputEmail)
#     else:
#         return redirect(url_for('login'))


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
    url_for('static', filename='signin.css')
    url_for('static', filename='bootstrap.min.css')
