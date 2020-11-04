import cv2

face_cascade = cv2.CascadeClassifier('C:/Users/judy8/Downloads/haarcascade_frontalface_default.xml')

#imgnum = 10
#img = cv2.imread('C:/Users/judy8/Downloads/data_set\image_data/ksm/ksm' + str(imgnum) + ".jpg")
imglist =[]

'''
이미지  불러오기
for i  in range(0,10):
    img = cv2.imread('C:/Users/judy8/Downloads/data_set\image_data/ksm/ksm' + str(i+1) + ".jpg")
    imglist.append(img)

'''    
for i  in range(0,10):
    img = cv2.imread('C:/Users/judy8/Downloads/data_set\image_data/jhr/jhr' + str(i+1) + ".jpg")
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    faces = face_cascade.detectMultiScale(gray, 1.3,5)
    for (x,y,w,h) in faces:
        cv2.rectangle(img, (x,y), (x+w, y+h), (255,0,0), 2)
        cropped = img[y - int(h/10):y + h + int(h/10),x - int(w/10):x + w + int(w/10)]
        cv2.imwrite("cropped jhr" + str(i+1) + ".jpg", cropped )
        roi_gray = gray[y:y+h, x:x+w]
        roi_color = img[y:y+h, x:x+w]
        
        #cv2.imshow('Image view',cropped)
        cv2.waitKey(0)
        cv2.destroyAllWindows()
    


