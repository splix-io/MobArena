import sys
import _thread
import time

import matplotlib
import matplotlib.pyplot as plt
from matplotlib.ticker import MaxNLocator
from matplotlib import cm
from mpl_toolkits.mplot3d import Axes3D

import numpy
from numpy.random import randn
from scipy import array, newaxis

print ('Number of Arguments: ', sys.argv[1], ' arguments.')
toFormat = sys.argv[1]
toFm = toFormat.split(":")
toFormat = []
for ln in toFm:
    if len(ln) > 0:
        temp = []
        for toChange in ln.split(","):
            temp.append(float(toChange))
        toFormat.append(temp)
print(toFormat)
DATA = array(toFormat)

Xs = DATA[:, 0]
Ys = DATA[:, 1]
Zs = DATA[:, 2]

def on_close(event):
    event.canvas.figure.axes[0].has_been_closed = True
    raise SystemExit

fig = plt.figure()
ax = plt.axes(projection='3d')
ax.has_been_closed = False
plt.ion()

fig.canvas.mpl_connect('close_event', on_close)

def LiveUpdator(fig, ax, length, flipped):
    if flipped:
        length += 1
    else:
        length -= 1
    ax.cla()
    Xs = DATA[length:, 0]
    Ys = DATA[length:, 1]
    Zs = DATA[length:, 2]
    ax.scatter3D(Xs, Ys, Zs, color="green")


    ax.xaxis.set_major_locator(MaxNLocator(5))
    ax.yaxis.set_major_locator(MaxNLocator(6))
    ax.zaxis.set_major_locator(MaxNLocator(5))

    plt.pause(0.01)
    if ax.has_been_closed:
        raise SystemExit
    return length


length = len(DATA)
print(length)
flipped = False
while True:
    length = LiveUpdator(fig, ax, length, flipped)
    if not flipped:
        if length == 0:
            flipped = True
    else:
        if length == len(DATA):
            flipped = False


plt.ioff()
plt.show()