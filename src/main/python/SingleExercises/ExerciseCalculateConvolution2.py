
# Calculate convolution for
# h[n] = delta[n] - delta[n-1]
# and
# x[n] = n for n>= 0, 0 otherwise
#
# Convolution is defined as:
# y[n] = sum from k = -inf to inf of x[k] * h[n-k]
#

def getDiracImpulseValue(n):
    if n == 0:
        return 1
    return 0

def getValueOfImpulse(n):
    return getDiracImpulseValue(n) - getDiracImpulseValue(n - 1)

def getValueOfSignal(n):
    if n < 0:
        return 0
    return n


impulseLength = 4
signalLength = 4
for n in range(-1, signalLength - 1):
    y = 0
    for k in range(-1, impulseLength - 1):
        y += getValueOfSignal(k) * getValueOfImpulse(n - k)
    print('y[', n,'] = ',  y)