import numpy as np
from scipy.io import wavfile
import matplotlib.pyplot as plt
import os


def plot_fft_of_wav_file(wav_file):
    samplerate, data = wavfile.read(wav_file)

    if len(data.shape) > 1:
        data = data[:, 0]

    fft_data = np.fft.fft(data)

    freqs = np.fft.fftfreq(len(fft_data), 1 / samplerate)

    plt.figure()
    plt.plot(freqs, np.abs(fft_data))
    plt.xlabel("Frequenz (Hz)")
    plt.ylabel("Amplitude")
    plt.title("FFT der WAV-Datei")
    plt.show()


dir = os.path.dirname(__file__)
file = os.path.join(dir, 'ringbackB.wav')

plot_fft_of_wav_file(file)









