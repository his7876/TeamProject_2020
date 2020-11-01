import pymysql
import numpy as np

def classification_performance_eval(y,y_predict):
    
    tp, tn, fp, fn = 0, 0, 0, 0
    
    for y, yp in zip(y,y_predict):#두개의 array동시에 for문 돌리려고 쓰는 zip
        if  y == 1 and yp == 1:
            tp += 1
            
        elif y == 1 and yp == -1:
            fn += 1 
        elif y == -1 and yp == 1:
            fp += 1
        else:
            tn += 1
            
    if (tp + fp == 0) and (tp + fn ==0):
        accuracy = (tp + tn)/(tp + tn + fp + fn)
        precision = 0
        recall = 0
        
           
    elif (tp + fp != 0) and (tp + fn ==0):
        accuracy = (tp + tn)/(tp + tn + fp + fn)
        precision = tp / (tp + fp)
        recall = 0
       
       
    elif (tp + fp == 0) and (tp + fn != 0):
        accuracy = (tp + tn)/(tp + tn + fp + fn)
        precision = 0
        recall = tp / (tp + fn)
        
    else:
        accuracy = (tp + tn)/(tp + tn + fp + fn)
        precision = tp / (tp + fp)
        recall = tp / (tp + fn)
             
       
    return accuracy, precision, recall  
        
            

conn = pymysql.connect(host='localhost',user = 'root', password='ols0120654',db = 'a')
curs = conn.cursor(pymysql.cursors.DictCursor)

sql = "select * from db_score_3"
curs.execute(sql)


data = curs.fetchall()

curs.close()
conn.close()


X = [ (t['homework'], t['discussion'], t['midterm'] ) for t in data]
X = np.array(X)

#feature 변수들을 training하기 위한 인풋으로 변환 완료
Y = [1 if (t['grade'] == 'B') else -1 for t in data]
Y = np.array(Y)
#Label



from sklearn.svm import SVC
from sklearn.model_selection import KFold

accuracy = []
precision = []
recall = []
f1_score = []

kf = KFold(n_splits = 7, random_state = 42, shuffle = True)

for train_index, test_index in kf.split(X):
    X_train, X_test = X[train_index], X[test_index]
    Y_train, Y_test = Y[train_index], Y[test_index]
    
    svm_clf = SVC()
    svm_clf.fit(X_train,Y_train)
    pred = svm_clf.predict(X_test)
    
    acc, pre, rec = classification_performance_eval(Y_test, pred)
    
    if (pre + rec)== 0:
        f1 = 0
    else:
        f1 = 2 * pre * rec / (pre + rec)
        
        
    accuracy.append(acc)
    precision.append(pre)
    recall.append(rec)
    f1_score.append(f1)

import statistics

print("SVM KFold accuracy : ", statistics.mean(accuracy))
print("SVM KFold precision : ", statistics.mean(precision))
print("SVM KFold recall : ", statistics.mean(recall))   
print("SVM KFold f1 score : ", statistics.mean(f1_score))   
    












'''
from sklearn.svm import SVC
from sklearn.model_selection import KFold

accuracy = []
precision = []
recall = []
f1_score = []

kf = KFold(n_splits = 3, random_state = 42, shuffle = True)

for train_index, test_index in kf.split(X):
    X_train, X_test = X[train_index], X[test_index]
    Y_train, Y_test = Y[train_index], Y[test_index]
    
    svm = SVC()
    svm.fit(X_train,X_train)
    pred = svm.predict(X_test)
    
    acc, pre, rec = classification_performance_eval(Y_test, pred)
    
    if (pre + rec)== 0:
        f1 = None
    else:
        f1 = 2 * pre * rec / (pre + rec)
    
    accuracy.append(acc)
    precision.append(pre)
    recall.append(rec)
    f1_score.append(f1)
    
import statistics

print("average_accuracy = ", statistics.mean(accuracy))
print("average_precision = ", statistics.mean(precision))
print("average_recall = ", statistics.mean(recall))
print("average_f1 score = ", statistics.mean(f1_score))

'''












