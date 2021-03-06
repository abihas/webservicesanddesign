from flask import Flask, render_template
import json
import pymysql

cnx = pymysql.connect(user='hibernate', password='hibernate', host='127.0.0.1', database='hibernate')
cursor = cnx.cursor()

app = Flask(__name__)

@app.route('/user/', methods=['get'])
@app.route('/user/<id>', methods=['get'])
def user(id=None):
  query = "select * from users"
  if (id): query = query + " where id={}".format(id)

  cursor.execute(query)
  rv = []
  for (id,username,password) in cursor:
    rv.append({'id':id, 'username':username, 'password':password})

  return json.dumps(rv)

@app.route('/betteruser', methods=['GET'])
@app.route('/betteruser/<id>', methods=['GET'])
def aanmelden(id=None):
    # opgave week 4
    query = "select * from users"
    if (id):
        query = query + " where id={}".format(id)

    cursor.execute(query)
    rv = []
    for (id, username, password) in cursor:
        rv.append({'id': id, 'username': username, 'password': password})

    return render_template("user.html", method=['GET'], gegevens=rv)


if __name__=='__main__':
  app.run(debug=True)

