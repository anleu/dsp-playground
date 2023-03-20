import scipy.fft as fft
import numpy as np
import matplotlib.pyplot as plt

N = 1000
T = 1.0 / 1000.0
frequency = 20

x = np.linspace(0.0, N * T, N)
y = np.sin(frequency * 2.0 * np.pi * x)

plt.plot(x, y)
plt.show()

yf = fft.fft(y)
xf = np.linspace(0.0, 1.0 / (2.0 * T), N // 2)

plt.plot(xf, 2.0 / N * np.abs(yf[0:N // 2]))
plt.grid()
plt.show()
