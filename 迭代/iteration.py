from collections import Iterable

d = {'a':1,'b':2,'c':3}
for index in d:
	print(index)
	


print(isinstance(d,Iterable))

c = ['wang','zuo','yang'];

for i,index in enumerate(c):
	print(i,index)