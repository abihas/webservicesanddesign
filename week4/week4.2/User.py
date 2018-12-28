import json

from sqlalchemy import Column, Integer, String, create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

engine = create_engine('mysql+pymysql://hibernate:hibernate@localhost/hibernate')
Base = declarative_base()
Session = sessionmaker(bind=engine)
activeSession = Session()


class User(Base):
    __tablename__ = 'users'

    id = Column(Integer, primary_key=True)
    username = Column('username', String(64))
    password = Column('password', String(128))

    def __repr__(self):
        return "<User(username='%s', password='%s')>" % \
                (self.username, self.password)

    def to_json(self):
        rv = {'username': self.username,
              'password': self.password,
              'id': self.id}
        return json.dumps(rv)

    @staticmethod
    def all_users():
        allUsers = activeSession.query(User).all()
        rv = ""
        for user in allUsers:
            rv += user.to_json()
        return rv

    def get_user(self):
        oneUser = activeSession.query(User).filter_by(id=self).first()
        if oneUser:
            rv = oneUser.to_json()
        else:
            rv = "Error: user does not exist."
        return rv
